package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.SkillItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentSkillsBinding
import com.nikitha.toknresumebuilder.model.Skill
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel

class SkillsFragment : Fragment() {

    private lateinit var binding: FragmentSkillsBinding

    private var skillsList = ArrayList<Skill>()
    private var ratingSelected ="none"
    private lateinit var resumeViewModel : ResumeViewModel
    val spinner_item = arrayOf("No ratings", "Stars", "Progress bar")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSkillsBinding.inflate(layoutInflater)

        val adapter = context?.let { ArrayAdapter(it, R.layout.spinner_row, R.id.cust_view, spinner_item) }
        binding.spinner.adapter =adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Sections"
        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]
        val resumeId = arguments?.getInt("resumeId")

        val skillsItem = Skill("", "", 1, resumeId!! )
        skillsList.add(skillsItem)

        var adapter = SkillItemAdapter(skillsList, "")
        binding.rvSkills.adapter =adapter
        binding.rvSkills.layoutManager =LinearLayoutManager(context)
        adapter.notifyItemInserted(0)

        binding.tvAddSkillSection.setOnClickListener {

            val skills_new = Skill("", "", 1, resumeId)
            skillsList.add(skills_new)

            adapter = SkillItemAdapter(skillsList, ratingSelected )
            binding.rvSkills.adapter =adapter
            binding.rvSkills.layoutManager =LinearLayoutManager(context)
            adapter.notifyItemInserted(skillsList.size)
        }

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {

                when(position)
                {
                    1->ratingSelected = "star"
                    2->ratingSelected = "progressBar"
                }


                adapter = SkillItemAdapter(skillsList, ratingSelected)
                binding.rvSkills.adapter = adapter
                binding.rvSkills.layoutManager = LinearLayoutManager(context)
                adapter.notifyDataSetChanged()

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext

        binding.btnSave.setOnClickListener {

            skillsList.forEach {
                resumeViewModel.insertSkillDetails(it)
            }

            val b = Bundle()
            b.putInt("resumeId", resumeId)
            NavHostFragment.findNavController(this).navigate(R.id.action_skillsFragment_to_objectiveFragment, b)

        }
    }


}
