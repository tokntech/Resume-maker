package com.nikitha.toknresumebuilder.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.ProfessionalDetails

class ProfessionItemAdapter(private val professionalDetails: ArrayList< ProfessionalDetails>) : RecyclerView.Adapter<ProfessionItemAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvProfNum: TextView? = itemView.findViewById(R.id.tvProfNum)
        var etProfile: EditText? = itemView.findViewById(R.id.etProfile)
        var etOrganization: EditText? =  itemView.findViewById(R.id.etOrganization)
        var etProfScore: EditText? =  itemView.findViewById(R.id.etProfScore)
        var wfhCheckBox : CheckBox = itemView.findViewById(R.id.wfhCheckBox)
        var etProfLocation: EditText? =  itemView.findViewById(R.id.etProfLocation)
        var etProfStartYear: EditText? =  itemView.findViewById(R.id.etProfStartYear)
        var etProfEndYear: EditText? =  itemView.findViewById(R.id.etProfEndYear)
        var etJobDesc: EditText? = itemView.findViewById(R.id.etProfDesc)

        fun bind(position: Int)
        {
            val index = position +1
            tvProfNum?.text = "Experience $index"
            wfhCheckBox.text = "Work from home"


            etProfile?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails[position].profile = etProfile!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etOrganization?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).organization= etOrganization!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProfScore?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).grade = etProfScore!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProfLocation?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).work_location = etProfLocation!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProfStartYear?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).work_Startyear = etProfStartYear!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProfEndYear?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).work_EndYear = etProfEndYear!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etJobDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    professionalDetails.get(position).job_description = etJobDesc!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_professional_item, parent, false)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etProfile?.setText(professionalDetails[position].profile)
        holder.etOrganization?.setText(professionalDetails[position].organization)
        holder.etProfScore?.setText(professionalDetails[position].grade)
        holder.wfhCheckBox.setText(professionalDetails[position].wfh)
        holder.etProfLocation?.setText(professionalDetails[position].work_location)
        holder.etProfStartYear?.setText(professionalDetails[position].work_Startyear)
        holder.etProfEndYear?.setText(professionalDetails[position].work_EndYear)
        holder.etJobDesc?.setText(professionalDetails[position].job_description)

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return professionalDetails.size
    }

}