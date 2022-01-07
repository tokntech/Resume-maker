package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.SkillItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentSkillsBinding
import com.nikitha.toknresumebuilder.model.PersonalDetails
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

        val toolbar  = activity?.findViewById<Toolbar>(R.id.holder_toolbar)
        toolbar?.findViewById<ImageView>(R.id.ivtips)?.visibility = View.VISIBLE
        toolbar?.findViewById<ImageView>(R.id.ivPreview)?.visibility = View.VISIBLE

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]
        var resumeId = arguments?.getInt("resumeId")

        if(resumeId == 0)
        {
            val personalDetails = PersonalDetails(0, "", "", "", "", "","","","","","")
            resumeId = resumeViewModel.insertPersonalDetails(personalDetails).toInt()
        }

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

        binding.btnPrevious.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

    }


}
