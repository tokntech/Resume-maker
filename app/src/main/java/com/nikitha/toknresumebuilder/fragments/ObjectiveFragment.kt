package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.databinding.FragmentObjectiveBinding
import com.nikitha.toknresumebuilder.model.Objective
import com.nikitha.toknresumebuilder.model.PersonalDetails
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel

class ObjectiveFragment : Fragment() {

private lateinit var binding: FragmentObjectiveBinding
private lateinit var resumeViewModel : ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentObjectiveBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

        val toolbar  = activity?.findViewById<Toolbar>(R.id.holder_toolbar)
        toolbar?.findViewById<ImageView>(R.id.ivtips)?.visibility = View.VISIBLE
        toolbar?.findViewById<ImageView>(R.id.ivPreview)?.visibility = View.VISIBLE

        activity?.title = "Sections"

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]
        var resumeId = arguments?.getInt("resumeId")

        if(resumeId == 0)
        {
            val personalDetails = PersonalDetails(0, "", "", "", "", "","","","","","")
            resumeId = resumeViewModel.insertPersonalDetails(personalDetails).toInt()
        }

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext

        binding.btnSave.setOnClickListener{
            val objective = Objective(resumeId!!, binding.etObjective.text.toString())
                       resumeViewModel.updateObjective(objective)

                       val b = Bundle()
                       b.putInt("resumeId", resumeId)

                       NavHostFragment.findNavController(this).navigate(R.id.action_objectiveFragment_to_projectFragment,b)
        }

        binding.btnPrevious.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

    }

}