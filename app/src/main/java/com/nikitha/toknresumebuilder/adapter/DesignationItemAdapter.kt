package com.nikitha.toknresumebuilder.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.Designation

class DesignationItemAdapter(private val designationDetails: ArrayList< Designation>) : RecyclerView.Adapter<DesignationItemAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var etDesignation: EditText? = itemView.findViewById(R.id.etDesignation)
        var etDuration: EditText? =  itemView.findViewById(R.id.etDuration)
        var etJobDesc: EditText? =  itemView.findViewById(R.id.etJobDesc)

        fun bind(position: Int)
        {
            etDesignation?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    designationDetails[position].designation_name = etDesignation!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etDuration?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    designationDetails.get(position).duration= etDuration!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etJobDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    designationDetails.get(position).job_description = etJobDesc!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_designation_item, parent, false)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etJobDesc?.setText(designationDetails[position].job_description)
        holder.etDesignation?.setText(designationDetails[position].designation_name)
        holder.etDuration?.setText(designationDetails[position].duration)

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return designationDetails.size
    }

}