package com.nikitha.toknresumebuilder.fragments

import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.EducationItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentEducationDetailsBinding
import com.nikitha.toknresumebuilder.model.EducationDetails
import java.util.*

class EducationDetailsFragment : Fragment() {
    private lateinit var binding : FragmentEducationDetailsBinding

    private var academicDetailsItems: ArrayList<EducationDetails> = ArrayList<EducationDetails>()

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
        activity?.title = "Education"

        val educationDetails = EducationDetails("", "", "", "", "" )
        academicDetailsItems.add(educationDetails)

        var adapter = EducationItemAdapter(academicDetailsItems, activity)
        binding.rvEduDetails.adapter = adapter
        binding.rvEduDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.tvAddEduSection.setOnClickListener {

            val educationDetails_new = EducationDetails("", "", "", "", "" )
            academicDetailsItems.add(educationDetails_new)

            adapter = EducationItemAdapter(academicDetailsItems, activity)
            binding.rvEduDetails.adapter = adapter
            binding.rvEduDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener{
            val fragment = ProfessionalDetailsFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext
    }


}