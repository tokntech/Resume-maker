package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.EducationItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentEducationDetailsBinding
import com.nikitha.toknresumebuilder.helper.OnStartDragListener
import com.nikitha.toknresumebuilder.helper.SimpleItemTouchHelperCallback
import com.nikitha.toknresumebuilder.model.EducationalDetails
import com.nikitha.toknresumebuilder.model.PersonalDetails
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel
import java.util.*

class EducationDetailsFragment : Fragment() , OnStartDragListener {
    private lateinit var binding : FragmentEducationDetailsBinding
    private lateinit var resumeViewModel : ResumeViewModel
    private var mItemTouchHelper: ItemTouchHelper? = null
    private lateinit var callback  : SimpleItemTouchHelperCallback

    private var academicDetailsItems: ArrayList<EducationalDetails> = ArrayList<EducationalDetails>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true *//* enabled by default *//*) {
                override fun handleOnBackPressed() {
                    Toast.makeText(context, "test back", Toast.LENGTH_SHORT).show()
                    //NavHostFragment.findNavController(this@EducationDetailsFragment).navigate(R.id.action_educationDetailsFragment_to_sectionsFragment)

                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEducationDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Sections"

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

        val toolbar  = activity?.findViewById<Toolbar>(R.id.holder_toolbar)
        toolbar?.findViewById<ImageView>(R.id.ivtips)?.visibility = View.VISIBLE
        toolbar?.findViewById<ImageView>(R.id.ivPreview)?.visibility = View.VISIBLE

        var resumeId = arguments?.getLong("resumeId")?.toInt()

        if(resumeId == 0)
        {
            val personalDetails = PersonalDetails(0, "", "", "", "", "","","","","","")
            resumeId = resumeViewModel.insertPersonalDetails(personalDetails).toInt()
        }

        val educationDetails = EducationalDetails("", "", "", "", "" , "", resumeId!!)
        academicDetailsItems.add(educationDetails)

        var adapter = EducationItemAdapter(academicDetailsItems, activity,this)
        binding.rvEduDetails.adapter = adapter
        binding.rvEduDetails.layoutManager = LinearLayoutManager(context)

        callback = SimpleItemTouchHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(binding.rvEduDetails)

        adapter.notifyDataSetChanged()

        binding.tvAddEduSection.setOnClickListener {

            val educationDetails_new = EducationalDetails("", "", "", "", "" , "", resumeId)
            academicDetailsItems.add(educationDetails_new)

            adapter = EducationItemAdapter(academicDetailsItems, activity, this)
            binding.rvEduDetails.adapter = adapter
            binding.rvEduDetails.layoutManager = LinearLayoutManager(context)

            callback = SimpleItemTouchHelperCallback(adapter)

            mItemTouchHelper = ItemTouchHelper(callback)
            mItemTouchHelper!!.attachToRecyclerView(binding.rvEduDetails)

            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener{

            academicDetailsItems.forEach{
                resumeViewModel.insertEducationalDetails(it)
            }


            val b = Bundle()
            b.putInt("resumeId", resumeId)

            NavHostFragment.findNavController(this).navigate(R.id.action_educationDetailsFragment_to_professionalDetailsFragment, b)
        }

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext

        binding.btnPrevious.setOnClickListener {
            NavHostFragment.findNavController(this).popBackStack()
        }

    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        if (viewHolder != null) {
            mItemTouchHelper?.startDrag(viewHolder)
        }
    }


    fun enableDragAndDrop(){
        callback.setmDraggable(true)
    }
}