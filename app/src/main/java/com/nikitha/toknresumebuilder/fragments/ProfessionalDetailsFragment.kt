package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.AbsoluteSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.nikitha.toknresumebuilder.adapter.ProfessionItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentProfessionalDetailsBinding
import com.nikitha.toknresumebuilder.helper.OnStartDragListener
import com.nikitha.toknresumebuilder.helper.SimpleItemTouchHelperCallback
import com.nikitha.toknresumebuilder.model.Designation
import com.nikitha.toknresumebuilder.model.PersonalDetails
import com.nikitha.toknresumebuilder.model.ProfessionalDetails
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel


class ProfessionalDetailsFragment : Fragment(), OnStartDragListener {

private lateinit var binding : FragmentProfessionalDetailsBinding
    private var professionalDetailsItems: ArrayList<ProfessionalDetails> = ArrayList()
    private var designationItems: ArrayList<Designation> = ArrayList()
    private lateinit var resumeViewModel : ResumeViewModel

    private var mItemTouchHelper: ItemTouchHelper? = null
    private lateinit var callback  : SimpleItemTouchHelperCallback

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

        resumeViewModel = ViewModelProvider(this)[ResumeViewModel::class.java]
        var resumeId = arguments?.getInt("resumeId")

        if(resumeId == 0)
        {
            val personalDetails = PersonalDetails(0, "", "", "", "", "","","","","","")
            resumeId = resumeViewModel.insertPersonalDetails(personalDetails).toInt()
        }

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)

        val toolbar  = activity?.findViewById<Toolbar>(R.id.holder_toolbar)
        toolbar?.findViewById<ImageView>(R.id.ivtips)?.visibility = View.VISIBLE
        toolbar?.findViewById<ImageView>(R.id.ivPreview)?.visibility = View.VISIBLE

       val designation = arrayListOf(
           Designation("","","")
       )

        val professionalDetails = ProfessionalDetails("", "", "",designation, resumeId!!)
        professionalDetailsItems.add(professionalDetails)

        designationItems.addAll(designation)

        val btntext = ("Save \n& proceed").toSpannable()
        btntext[5..15] = AbsoluteSizeSpan(10 , true)
        binding.btnSave.text = btntext


        var adapter = ProfessionItemAdapter(professionalDetailsItems, activity, this)

        binding.rvProfDetails.adapter = adapter
        binding.rvProfDetails.layoutManager = LinearLayoutManager(context)

        callback = SimpleItemTouchHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(binding.rvProfDetails)

        adapter.notifyDataSetChanged()

        binding.tvAddProfSection.setOnClickListener {

            val profDetails_new = ProfessionalDetails("", "", "", designation, resumeId)
            professionalDetailsItems.add(profDetails_new)

            adapter = ProfessionItemAdapter(professionalDetailsItems, activity, this)
            binding.rvProfDetails.adapter = adapter
            binding.rvProfDetails.layoutManager = LinearLayoutManager(context)
            adapter.notifyDataSetChanged()
        }

        binding.btnSave.setOnClickListener{

            professionalDetailsItems.forEach {
                resumeViewModel.insertProfessionalDetails(it)
            }

            val b = Bundle()
            b.putInt("resumeId", resumeId)

            NavHostFragment.findNavController(this).navigate(R.id.action_professionalDetailsFragment_to_skillsFragment, b)

        }

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