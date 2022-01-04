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
import com.nikitha.toknresumebuilder.adapter.DesignationItemAdapter
import com.nikitha.toknresumebuilder.adapter.ProfessionItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentProfessionalDetailsBinding
import com.nikitha.toknresumebuilder.model.Designation
import com.nikitha.toknresumebuilder.model.ProfessionalDetails
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel
import java.util.*
import kotlin.collections.ArrayList


class ProfessionalDetailsFragment : Fragment() {

private lateinit var binding : FragmentProfessionalDetailsBinding
    private var professionalDetailsItems: ArrayList<ProfessionalDetails> = ArrayList()
    private var designationItems: ArrayList<Designation> = ArrayList()
    private lateinit var resumeViewModel : ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfessionalDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Sections"

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]
        val resumeId = arguments?.getInt("resumeId")

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

       val designation = arrayListOf(
           Designation("","","")
       )

        val professionalDetails = ProfessionalDetails("", "", "",designation, resumeId!!)
        professionalDetailsItems.add(professionalDetails)

        designationItems.addAll(designation)

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext


        var adapter = ProfessionItemAdapter(professionalDetailsItems)

        binding.rvProfDetails.adapter = adapter
        binding.rvProfDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.tvAddProfSection.setOnClickListener {

            var profDetails_new = ProfessionalDetails("", "", "", designation, resumeId)
            professionalDetailsItems.add(profDetails_new)

            adapter = ProfessionItemAdapter(professionalDetailsItems)
            binding.rvProfDetails.adapter = adapter
            binding.rvProfDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener{

            professionalDetailsItems.forEach {
                resumeViewModel.insertProfessionalDetails(it)
            }

            val b = Bundle()
            b.putInt("resumeId", resumeId)

            NavHostFragment.findNavController(this).navigate(R.id.action_professionalDetailsFragment_to_skillsFragment, b)

        }

    }
}