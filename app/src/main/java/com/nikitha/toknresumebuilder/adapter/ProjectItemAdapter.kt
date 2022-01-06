package com.nikitha.toknresumebuilder.adapter

import android.app.Dialog
import android.content.Context
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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.Projects
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ProjectItemAdapter(private val context: Context, private val projectList: ArrayList<Projects>): RecyclerView.Adapter<ProjectItemAdapter.ViewHolder>() {

    private var dateSelected = intArrayOf(0,1,2,3)
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            etProjectTitle?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    projectList[position].project_title = etProjectTitle.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProjectDuration?.setOnClickListener {

                //region
                val dialog = Dialog(context)
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

                    projectList[position].start_date = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + calendar.get(
                        Calendar.YEAR)
                    projectList[position].end_date = end_calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + end_calendar.get(
                        Calendar.YEAR).toString()

                    etProjectDuration!!.setText( projectList[position].start_date + "to" +projectList[position].end_date)

                    dialog.dismiss()
                }
                dialog.show()

                //end region
            }

            etProjectDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    projectList[position].description = etProjectDesc.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProjectLinkLayout?.setEndIconOnClickListener {
                val customDialog = Dialog(context)
                customDialog.setContentView(R.layout.dialog_linkedin_url)
                customDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val yesBtn = customDialog.findViewById(R.id.tvOk) as TextView
                val noBtn = customDialog.findViewById(R.id.tvCancel) as TextView

                yesBtn.setOnClickListener {
                    projectList[position].projText  = customDialog.findViewById<TextInputEditText>(R.id.etLinkedInText).text.toString()
                    projectList[position].projLink  = customDialog.findViewById<TextInputEditText>(R.id.etLinkedInUrl).text.toString()

                    customDialog.dismiss()
                }
                noBtn.setOnClickListener {
                    customDialog.dismiss()
                }
                customDialog.show()
                customDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }

        }

        val etProjectTitle : EditText? = itemView.findViewById(R.id.etTitle)
        val etProjectDuration : EditText? = itemView.findViewById(R.id.etProjDuration)
        val etProjectDesc : EditText? = itemView.findViewById(R.id.etProjDesc)
        val etProjectLink : EditText? = itemView.findViewById(R.id.etProjLink)
        val etProjectLinkLayout: TextInputLayout? = itemView.findViewById(R.id.etProjLinkLayout)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectItemAdapter.ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_project_item, parent, false)))
    }

    override fun onBindViewHolder(holder: ProjectItemAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }




}