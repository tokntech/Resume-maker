
package com.nikitha.toknresumebuilder.commonactivities

import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.nikitha.toknresumebuilder.R
import java.util.*


/*import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.nikitha.toknresumebuilder.databinding.DialogMonthYearPickerBinding
import java.util.*

class MonthYearPickerDialog(private val date: Date = Date()) : DialogFragment() {

    companion object {
        private const val MAX_YEAR = 2099
    }
    private lateinit var binding : DialogMonthYearPickerBinding

    private var listener: DatePickerDialog.OnDateSetListener? = null

    fun setListener(listener: DatePickerDialog.OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogMonthYearPickerBinding.inflate(requireActivity().layoutInflater)
        val cal: Calendar = Calendar.getInstance().apply { time = date }

        binding.pickerMonth.run {
            minValue = 0
            maxValue = 11
            value = cal.get(Calendar.MONTH)
            displayedValues = arrayOf("Jan","Feb","Mar","Apr","May","June","July",
                "Aug","Sep","Oct","Nov","Dec")
        }

        binding.pickerYear.run {
            val year = cal.get(Calendar.YEAR)
            minValue = year
            maxValue = MAX_YEAR
            value = year
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Please Select View Month")
            .setView(binding.root)
            .setPositiveButton("Ok")
            { _, _ -> listener?.onDateSet(null, binding.pickerYear.value, binding.pickerMonth.value, 1)
            }
            .setNegativeButton("Cancel") { _, _ -> dialog?.cancel() }
            .create()
    }
}*/

class MonthYearPickerDialog : DialogFragment() {
    private var listener: OnDateSetListener? = null

    fun setListener(listener: OnDateSetListener?) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        // Get the layout inflater

        val inflater: LayoutInflater = activity!!.layoutInflater

        val cal: Calendar = Calendar.getInstance()

        val dialog: View = inflater.inflate(R.layout.dialog_month_year_picker, null)

        val monthPicker = dialog.findViewById(R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById(R.id.picker_year) as NumberPicker

        monthPicker.minValue = 0
        monthPicker.maxValue = 11
        monthPicker.value = cal.get(Calendar.MONTH)

        val year: Int = cal.get(Calendar.YEAR)

        yearPicker.minValue = year
        yearPicker.maxValue = MAX_YEAR
        yearPicker.value = year

        builder.setView(dialog) // Add action buttons
            .setPositiveButton(android.R.string.ok) {
                    _, _ ->
                listener!!.onDateSet(
                    null,
                    yearPicker.value,
                    monthPicker.value,
                    0
                )
            }
            .setNegativeButton(android.R.string.cancel
            ) { _, _ ->
                this@MonthYearPickerDialog.dialog?.cancel()
            }
        return builder.create()
    }

    companion object {
        private const val MAX_YEAR = 2099
    }
}
