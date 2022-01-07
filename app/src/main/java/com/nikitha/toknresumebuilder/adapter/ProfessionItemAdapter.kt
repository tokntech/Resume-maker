package com.nikitha.toknresumebuilder.adapter

import android.app.Activity
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.MenuCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.fragments.ProfessionalDetailsFragment
import com.nikitha.toknresumebuilder.helper.ItemTouchHelperAdapter
import com.nikitha.toknresumebuilder.model.Designation
import com.nikitha.toknresumebuilder.model.ProfessionalDetails
import java.util.*
import kotlin.collections.ArrayList

class ProfessionItemAdapter(private val professionalDetails: ArrayList< ProfessionalDetails>,
                            private val activity: Activity?,
                            private val professionalDetailsFragment: ProfessionalDetailsFragment
                        ) : RecyclerView.Adapter<ProfessionItemAdapter.ViewHolder>(),
    ItemTouchHelperAdapter
{
    private val viewPool = RecyclerView.RecycledViewPool()

    private var designationItems: java.util.ArrayList<Designation> = java.util.ArrayList<Designation>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var etCompany: EditText? = itemView.findViewById(R.id.etCompany)
        var etLocation: EditText? =  itemView.findViewById(R.id.etLoc)
        var etDesc: EditText? =  itemView.findViewById(R.id.etCompDesc)
        var rvDesignation: RecyclerView? = itemView.findViewById(R.id.rvDesignation)
        var tvAddDesignation : TextView? = itemView.findViewById(R.id.tvAddDesigSection)
        var iboptions: ImageView? = itemView.findViewById(R.id.ibProfOptions)


        @RequiresApi(Build.VERSION_CODES.Q)
        fun bind(position: Int) {
            etCompany?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails[position].company_name = etCompany!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etLocation?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).location = etLocation!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).description = etDesc!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })


            val designation = Designation("", "", "")
            designationItems.add(designation)

            val designationLayoutManager =
                LinearLayoutManager(rvDesignation?.context, RecyclerView.VERTICAL, false)
            designationLayoutManager.initialPrefetchItemCount = 1
            rvDesignation?.apply {
                layoutManager = designationLayoutManager
                adapter = DesignationItemAdapter(designationItems, activity)
                setRecycledViewPool(viewPool)
            }

            tvAddDesignation?.setOnClickListener {
                val designation_new = Designation("", "", "", "")
                designationItems.add(designation_new)

                val designationLayoutManager_new =
                    LinearLayoutManager(rvDesignation?.context, RecyclerView.VERTICAL, false)
                designationLayoutManager_new.initialPrefetchItemCount = designationItems.size
                rvDesignation?.apply {
                    layoutManager = designationLayoutManager_new
                    adapter = DesignationItemAdapter(designationItems, activity)
                    setRecycledViewPool(viewPool)
                }
            }



            if (professionalDetails.size > 1 && position > 0) {
                iboptions?.visibility = View.VISIBLE
            }

            iboptions?.setOnClickListener {

                val popup = PopupMenu(activity, it)
                val inflater = popup.menuInflater
                popup.setForceShowIcon(true)
                MenuCompat.setGroupDividerEnabled(popup.menu, true)
                inflater.inflate(R.menu.options, popup.menu)

                popup.setOnMenuItemClickListener { p0 ->
                    if (p0?.itemId == R.id.rearrange) {
                        professionalDetailsFragment.enableDragAndDrop()
                    }
                    else
                    {
                        professionalDetails.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    true
                }


                popup.show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_professional_item, parent, false)))

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etCompany?.setText(professionalDetails[position].company_name)
        holder.etDesc?.setText(professionalDetails[position].description)
        holder.etLocation?.setText(professionalDetails[position].location)



        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return professionalDetails.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(professionalDetails, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        professionalDetails.removeAt(position)
        notifyItemRemoved(position)
    }

}