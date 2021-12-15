package com.nikitha.toknresumebuilder.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.Designation
import com.nikitha.toknresumebuilder.model.ProfessionalDetails

class ProfessionItemAdapter(private val professionalDetails: ArrayList< ProfessionalDetails>) : RecyclerView.Adapter<ProfessionItemAdapter.ViewHolder>()
{
    private val viewPool = RecyclerView.RecycledViewPool()

    private var designationItems: java.util.ArrayList<Designation> = java.util.ArrayList<Designation>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var etCompany: EditText? = itemView.findViewById(R.id.etCompany)
        var etLocation: EditText? =  itemView.findViewById(R.id.etLoc)
        var etDesc: EditText? =  itemView.findViewById(R.id.etCompDesc)
        var rvDesignation: RecyclerView? = itemView.findViewById(R.id.rvDesignation)
        var tvAddDesignation : TextView? = itemView.findViewById(R.id.tvAddDesigSection)


        fun bind(position: Int)
        {
            etCompany?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails[position].comp_name = etCompany!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etLocation?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).work_location= etLocation!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).comp_description = etDesc!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })


            val designation = Designation("","","")
            designationItems.add(designation)

            val designationLayoutManager = LinearLayoutManager(rvDesignation?.context, RecyclerView.VERTICAL, false)
            designationLayoutManager.initialPrefetchItemCount =1
            rvDesignation?.apply {
                layoutManager = designationLayoutManager
                adapter = DesignationItemAdapter(designationItems)
                setRecycledViewPool(viewPool)
            }

            tvAddDesignation?.setOnClickListener {
                val designation_new = Designation("","","")
                designationItems.add(designation_new)

                val designationLayoutManager_new = LinearLayoutManager(rvDesignation?.context, RecyclerView.VERTICAL, false)
                designationLayoutManager_new.initialPrefetchItemCount = designationItems.size
                rvDesignation?.apply {
                    layoutManager = designationLayoutManager_new
                    adapter = DesignationItemAdapter(designationItems)
                    setRecycledViewPool(viewPool)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_professional_item, parent, false)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etCompany?.setText(professionalDetails[position].comp_name)
        holder.etDesc?.setText(professionalDetails[position].comp_description)
        holder.etLocation?.setText(professionalDetails[position].work_location)



        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return professionalDetails.size
    }

}