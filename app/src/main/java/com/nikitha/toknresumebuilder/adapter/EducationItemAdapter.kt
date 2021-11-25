package com.nikitha.toknresumebuilder.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.EducationDetails

class EducationItemAdapter(private val educationDetails: ArrayList<EducationDetails>) : RecyclerView.Adapter<EducationItemAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvEduString: TextView? = itemView.findViewById(R.id.tvEduNum)
        var etCourseName: EditText? = itemView.findViewById(R.id.etCourse)
        var etSchoolName: EditText? =  itemView.findViewById(R.id.etSchool)
        var etScore: EditText? =  itemView.findViewById(R.id.etScore)
        var etStartYear: EditText? =  itemView.findViewById(R.id.etStartYear)
        var etEndYear: EditText? =  itemView.findViewById(R.id.etEndYear)

        fun bind(position: Int)
        {
            val index = position +1
            tvEduString?.text = "Education $index"



            etCourseName?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails[position].course = etCourseName!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etSchoolName?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails.get(position).school = etSchoolName!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etScore?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails.get(position).score = etScore!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etStartYear?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails.get(position).startYear = etStartYear!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etEndYear?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails.get(position).endYear = etEndYear!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.add_education_details, parent, false)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etCourseName?.setText(educationDetails[position].course)
        holder.etSchoolName?.setText(educationDetails[position].school)
        holder.etScore?.setText(educationDetails[position].score)
        holder.etStartYear?.setText(educationDetails[position].startYear)
        holder.etEndYear?.setText(educationDetails[position].endYear)

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return educationDetails.size
    }
}