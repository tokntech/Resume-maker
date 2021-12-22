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
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.SkillItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentSkillsBinding
import com.nikitha.toknresumebuilder.model.Skill

class SkillsFragment : Fragment() {

    private lateinit var binding: FragmentSkillsBinding

    private var skillsList = ArrayList<Skill>()
    private var ratingSelected ="star"
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

        val skillsItem = Skill("")
        skillsList.add(skillsItem)

        var adapter = SkillItemAdapter(skillsList, "")
        binding.rvSkills.adapter =adapter
        binding.rvSkills.layoutManager =LinearLayoutManager(context)
        adapter.notifyItemInserted(0)

        binding.tvAddSkillSection.setOnClickListener {

            val skills_new = Skill("")
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

                if(position == 1) {
                    ratingSelected = "star"
                    adapter = SkillItemAdapter(skillsList, ratingSelected)
                }
                else if(position == 2)
                {
                    ratingSelected = "progressBar"
                    adapter = SkillItemAdapter(skillsList, ratingSelected)
                }

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
            val fragment = ObjectiveFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }
    }


}
