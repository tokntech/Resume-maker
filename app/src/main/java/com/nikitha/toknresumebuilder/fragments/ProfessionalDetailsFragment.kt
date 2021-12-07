package com.nikitha.toknresumebuilder.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.adapter.ProfessionItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentProfessionalDetailsBinding
import com.nikitha.toknresumebuilder.model.ProfessionalDetails
import java.util.*


class ProfessionalDetailsFragment : Fragment() {

private lateinit var binding : FragmentProfessionalDetailsBinding
    private var professionalDetailsItems: ArrayList<ProfessionalDetails> = ArrayList<ProfessionalDetails>()

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

        activity?.title = "Professional Details"

        val professionalDetails = ProfessionalDetails("", "", "", "", "", "", "", "")
        professionalDetailsItems.add(professionalDetails)


        var adapter = ProfessionItemAdapter(professionalDetailsItems)
        binding.rvProfDetails.adapter = adapter
        binding.rvProfDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.btnProfAdd.setOnClickListener {

            var profDetails_new = ProfessionalDetails("", "", "", "", "", "", "", "")
            professionalDetailsItems.add(profDetails_new)

            adapter = ProfessionItemAdapter(professionalDetailsItems)
            binding.rvProfDetails.adapter = adapter
            binding.rvProfDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }
    }
}