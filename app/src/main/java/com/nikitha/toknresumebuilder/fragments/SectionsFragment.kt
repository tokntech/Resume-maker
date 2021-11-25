package com.nikitha.toknresumebuilder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSectionsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvPerSection.setOnClickListener {
            val fragment = PersonalDetailsFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }

        binding.tvEduSection.setOnClickListener {
            val fragment = EducationDetailsFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }

        binding.tvObjective.setOnClickListener {
            val fragment = ObjectiveFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }

        binding.tvRearrange.setOnClickListener {
            val fragment = RearrangeFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }


    }
}