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
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.DesignationItemAdapter
import com.nikitha.toknresumebuilder.adapter.ProfessionItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentProfessionalDetailsBinding
import com.nikitha.toknresumebuilder.model.Designation
import com.nikitha.toknresumebuilder.model.ProfessionalDetails
import java.util.*
import kotlin.collections.ArrayList


class ProfessionalDetailsFragment : Fragment() {

private lateinit var binding : FragmentProfessionalDetailsBinding
    private var professionalDetailsItems: ArrayList<ProfessionalDetails> = ArrayList<ProfessionalDetails>()
    private var designationItems: ArrayList<Designation> = ArrayList<Designation>()

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

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

       val designation = arrayListOf(
           Designation("","","")
       )

        val professionalDetails = ProfessionalDetails("", "", "",designation, 0)
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

            var profDetails_new = ProfessionalDetails("", "", "", designation, 0)
            professionalDetailsItems.add(profDetails_new)

            adapter = ProfessionItemAdapter(professionalDetailsItems)
            binding.rvProfDetails.adapter = adapter
            binding.rvProfDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener{
        }

    }
}