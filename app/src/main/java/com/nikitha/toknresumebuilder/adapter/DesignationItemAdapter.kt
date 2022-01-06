package com.nikitha.toknresumebuilder.adapter

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.Designation
import java.text.SimpleDateFormat
import java.util.*

class DesignationItemAdapter( private val designationDetails: ArrayList< Designation>, private val activity: Activity?) : RecyclerView.Adapter<DesignationItemAdapter.ViewHolder>()
{
    private var dateSelected = intArrayOf(0,1,2,3)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var etDesignation: EditText? = itemView.findViewById(R.id.etDesignation)
        var etDuration: EditText? =  itemView.findViewById(R.id.etProfDuration)
        var etJobDesc: EditText? =  itemView.findViewById(R.id.etJobDesc)

        fun bind(position: Int)
        {
            etDesignation?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    designationDetails[position].designation = etDesignation!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

           etDuration?.setOnClickListener {

               //region
               val dialog = Dialog(activity!!)
               dialog.setCancelable(true)
               dialog.setContentView(R.layout.dialog_month_year_picker)

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

               // If user doesn't select any, show the default values of current year and jan for month
               dateSelected[1] = year
               dateSelected[3] = year
               dateSelected[2] = 0

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

               startMonthPicker.setOnValueChangedListener { _, _, p2 -> dateSelected[0] = p2 }

               startYearPicker.setOnValueChangedListener { _, _, p2 -> dateSelected[1] = p2 }

               endMonthPicker.setOnValueChangedListener { _, _, p2 -> dateSelected[2] = p2 }

               endYearPicker.setOnValueChangedListener { _, _, p2 -> dateSelected[3] = p2 }

               btnCancel.setOnClickListener {
                   dialog.dismiss()
               }
               btnOk.setOnClickListener {

                   val stringStartDate=(dateSelected[0] +1).toString() + "-" + dateSelected[1].toString()
                   val stringEndDate=(dateSelected[2] +1).toString() + "-" + dateSelected[3].toString()

                   val dateFormatMMyyyy = SimpleDateFormat("MM-yyyy", Locale.ENGLISH)

                   val start_date = dateFormatMMyyyy.parse(stringStartDate)
                   val end_date = dateFormatMMyyyy.parse(stringEndDate)

                   val calendar = Calendar.getInstance()
                   calendar.time = start_date

                   val end_calendar = Calendar.getInstance()
                   end_calendar.time = end_date

                   //val stringStartDate=(dateSelected[0] +1).toString() + "-" + dateSelected[1].toString()

                   designationDetails[position].start_date = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + calendar.get(
                       Calendar.YEAR)
                   designationDetails[position].end_date = end_calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + end_calendar.get(
                       Calendar.YEAR).toString()

                   etDuration!!.setText( designationDetails[position].start_date + "to" +designationDetails[position].end_date)

                   dialog.dismiss()
               }
               dialog.show()

               //end region
           }


            etJobDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    designationDetails.get(position).description = etJobDesc!!.text.toString()
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
        holder.etJobDesc?.setText(designationDetails[position].description)
        holder.etDesignation?.setText(designationDetails[position].designation)
        holder.etDuration?.setText(designationDetails[position].start_date)

        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return designationDetails.size
    }

}