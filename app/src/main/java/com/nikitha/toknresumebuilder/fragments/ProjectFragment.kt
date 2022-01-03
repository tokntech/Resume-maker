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
import com.nikitha.toknresumebuilder.adapter.ProjectItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentProjectBinding
import com.nikitha.toknresumebuilder.model.Projects

class ProjectFragment : Fragment() {

    private lateinit var binding: FragmentProjectBinding
    private var projectsList : ArrayList<Projects> = ArrayList<Projects>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProjectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext

        activity?.title = "Sections"

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

        val projectItem = Projects("","","","", "", 0)
        projectsList.add(projectItem)


        var adapter = ProjectItemAdapter(projectsList)
        binding.rvProjDetails.adapter = adapter
        binding.rvProjDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.tvAddProjSection.setOnClickListener {

            val projectItem = Projects("","","","", "", 0)
            projectsList.add(projectItem)

            adapter = ProjectItemAdapter(projectsList)
            binding.rvProjDetails.adapter = adapter
            binding.rvProjDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_projectFragment_to_saveFragment)
        }

    }
}