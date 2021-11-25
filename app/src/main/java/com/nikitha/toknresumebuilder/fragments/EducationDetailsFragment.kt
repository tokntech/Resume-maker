package com.nikitha.toknresumebuilder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.EducationItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentEducationDetailsBinding
import com.nikitha.toknresumebuilder.model.EducationDetails
import java.util.ArrayList

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

        var educationDetails = EducationDetails("", "", "", "", "" )
        academicDetailsItems.add(educationDetails)

        var adapter = EducationItemAdapter(academicDetailsItems)
        binding.rvEduDetails.adapter = adapter
        binding.rvEduDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.btnEduAdd.setOnClickListener {

            var educationDetails_new = EducationDetails("", "", "", "", "" )
            academicDetailsItems.add(educationDetails_new)

            adapter = EducationItemAdapter(academicDetailsItems)
            binding.rvEduDetails.adapter = adapter
            binding.rvEduDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }
    }


}