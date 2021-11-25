package com.nikitha.toknresumebuilder.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.databinding.FragmentPersonalDetailsBinding
import com.nikitha.toknresumebuilder.model.PersonalDetails
import java.lang.StringBuilder

class PersonalDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPersonalDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPersonalDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Personal Details"

        binding.ivProfilePic.setOnClickListener{


        }
        binding.btnSave.setOnClickListener{
            Toast.makeText(context, binding.etName.text, Toast.LENGTH_SHORT).show()

            val personalDetails = PersonalDetails(  binding.etName.text.toString(),
                binding.etTitle.text.toString(),
                binding.etPhoneNum.text.toString(),
                binding.etEmail.text.toString(),
                binding.etWebsite.text.toString(),
                binding.etLinkedInUrl.text.toString())

            val fragment = PreviewFragment()

            val bundle = Bundle()
            bundle.putParcelable("Personal Details", personalDetails)
            fragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }
    }

}