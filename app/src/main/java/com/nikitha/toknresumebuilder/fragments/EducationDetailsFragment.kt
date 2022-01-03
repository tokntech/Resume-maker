package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.EducationItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentEducationDetailsBinding
import com.nikitha.toknresumebuilder.model.EducationalDetails
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel
import java.util.*

class EducationDetailsFragment : Fragment() {
    private lateinit var binding : FragmentEducationDetailsBinding
    private lateinit var resumeViewModel : ResumeViewModel

    private var academicDetailsItems: ArrayList<EducationalDetails> = ArrayList<EducationalDetails>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEducationDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Sections"

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

        val resumeId = arguments?.getLong("resumeId")?.toInt()

        val educationDetails = resumeId?.let { EducationalDetails("", "", "", "", "" , "", it) }
        if (educationDetails != null) {
            academicDetailsItems.add(educationDetails)
        }

        var adapter = EducationItemAdapter(academicDetailsItems, activity)
        binding.rvEduDetails.adapter = adapter
        binding.rvEduDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.tvAddEduSection.setOnClickListener {

            val educationDetails_new = EducationalDetails("", "", "", "", "" , "", 1)
            academicDetailsItems.add(educationDetails_new)

            adapter = EducationItemAdapter(academicDetailsItems, activity)
            binding.rvEduDetails.adapter = adapter
            binding.rvEduDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener{


            academicDetailsItems.forEach{
                resumeViewModel.insertEducationalDetails(it)
            }


            NavHostFragment.findNavController(this).navigate(R.id.action_educationDetailsFragment_to_professionalDetailsFragment)
        }

       /* binding.btnPrevious.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_educationDetailsFragment_to_sectionsFragment2)
        }*/

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext
    }


}