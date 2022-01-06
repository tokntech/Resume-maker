package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.databinding.FragmentSectionsBinding


class SectionsFragment : Fragment() {

    private lateinit var binding : FragmentSectionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object :OnBackPressedCallback(true){
            override fun handleOnBackPressed()
            {
                    isEnabled = false
                    activity?.onBackPressed()

                Log.d("Section", "Pressed back from section")
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSectionsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        activity?.title = "Sections"

        val resumeId = arguments?.getInt("resumeId")
        val b = Bundle()
        b.putInt("resumeId", resumeId!!)

        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tvPerSection.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_personalDetailsFragment ,b)
        }

        binding.tvEduSection.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_educationDetailsFragment ,b)
        }

        binding.tvProfDetails.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_professionalDetailsFragment ,b)
        }

        binding.tvObjective.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_objectiveFragment ,b)
        }

        binding.tvProject.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_projectFragment ,b)
        }

        binding.tvRearrange.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_rearrangeFragment ,b)
        }

        binding.tvSkills.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_sectionsFragment_to_skillsFragment ,b)
        }

        setHasOptionsMenu(true)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }
}