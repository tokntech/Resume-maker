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

        activity?.title = "Projects"

        val projectItem = Projects("","","","")
        projectsList.add(projectItem)


        var adapter = ProjectItemAdapter(projectsList)
        binding.rvProjDetails.adapter = adapter
        binding.rvProjDetails.layoutManager = LinearLayoutManager(context)
        adapter.notifyDataSetChanged()

        binding.tvAddProjSection.setOnClickListener {

            val projectItem = Projects("","","","")
            projectsList.add(projectItem)

            adapter = ProjectItemAdapter(projectsList)
            binding.rvProjDetails.adapter = adapter
            binding.rvProjDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

    }
}