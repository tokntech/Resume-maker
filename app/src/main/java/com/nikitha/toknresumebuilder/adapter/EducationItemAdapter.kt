package com.nikitha.toknresumebuilder.adapter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.commonactivities.MonthYearPickerDialog
import com.nikitha.toknresumebuilder.model.EducationDetails
import java.util.*
import kotlin.collections.ArrayList


class EducationItemAdapter(private val educationDetails: ArrayList<EducationDetails>, private val activity: Activity?) : RecyclerView.Adapter<EducationItemAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var etCourseName: EditText? = itemView.findViewById(R.id.etCourse)
        var etSchoolName: EditText? =  itemView.findViewById(R.id.etSchool)
        var etLocation:EditText? = itemView.findViewById(R.id.etLoc)
        var etDuration:EditText? = itemView.findViewById(R.id.etDuration)
        var keyachievements: EditText? = itemView.findViewById(R.id.etAchieve)


        fun bind(position: Int)
        {

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
                    educationDetails[position].school = etSchoolName!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etLocation?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails[position].location = etLocation!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etDuration?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                   // educationDetails.get(position).duration = etDuration!!.text.toString()
                    var dateSelected: IntArray
                    ViewDialog().apply {
                       dateSelected = showMonthYearDialog(activity)
                   }
                    educationDetails.get(position).duration = dateSelected[0].toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            keyachievements?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    educationDetails[position].keyAchievements = keyachievements!!.text.toString()
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
        holder.etDuration?.setText(educationDetails[position].duration)
        holder.etLocation?.setText(educationDetails[position].location)
        holder.keyachievements?.setText(educationDetails[position].keyAchievements)


        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return educationDetails.size
    }


}

class ViewDialog {
    fun showMonthYearDialog(activity: Activity?) : IntArray {
        val dialog = Dialog(activity!!)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_month_year_picker)

        var dateSelected= intArrayOf(0,1,2,3)

        val startMonthPicker = dialog.findViewById(R.id.picker_month) as NumberPicker
        val startYearPicker = dialog.findViewById(R.id.picker_year) as NumberPicker
        val btnCancel = dialog.findViewById<TextView>(R.id.btnCancel)
        val btnOk = dialog.findViewById<TextView>(R.id.btnOk)

        val cal: Calendar = Calendar.getInstance()

        startMonthPicker.run {
            startMonthPicker.minValue = 0
            startMonthPicker.maxValue = 11
            startMonthPicker.value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf("Jan","Feb","Mar","Apr","May","June","July",
                "Aug","Sep","Oct","Nov","Dec")
        }

        val year: Int = cal.get(Calendar.YEAR)

        startYearPicker.minValue = 1990
        startYearPicker.maxValue = 2099
        startYearPicker.value = year


        //End year picker
        val endMonthPicker = dialog.findViewById(R.id.picker_Endmonth) as NumberPicker
        val endYearPicker = dialog.findViewById(R.id.picker_Endyear) as NumberPicker

        endMonthPicker.run {
            endMonthPicker.minValue = 0
            endMonthPicker.maxValue = 11
            endMonthPicker.value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf("Jan","Feb","Mar","Apr","May","June","July",
                "Aug","Sep","Oct","Nov","Dec")
        }

        endYearPicker.minValue = 1990
        endYearPicker.maxValue = 2099
        endYearPicker.value = year

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        startMonthPicker.setOnValueChangedListener { p0, p1, p2 -> dateSelected[0] = p2 }

        startYearPicker.setOnValueChangedListener { p0, p1, p2 -> dateSelected[1] = p2 }

        endMonthPicker.setOnValueChangedListener { p0, p1, p2 -> dateSelected[2] = p2 }

        endYearPicker.setOnValueChangedListener { p0, p1, p2 -> dateSelected[3] = p2 }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }
        btnOk.setOnClickListener {

        }
        dialog.show()

        return dateSelected
    }
}
