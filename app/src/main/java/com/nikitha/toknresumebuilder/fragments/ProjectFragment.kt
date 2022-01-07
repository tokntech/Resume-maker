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
import com.nikitha.toknresumebuilder.adapter.ProjectItemAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentProjectBinding
import com.nikitha.toknresumebuilder.helper.OnStartDragListener
import com.nikitha.toknresumebuilder.helper.SimpleItemTouchHelperCallback
import com.nikitha.toknresumebuilder.model.PersonalDetails
import com.nikitha.toknresumebuilder.model.Projects
import com.nikitha.toknresumebuilder.viewmodel.ResumeViewModel

class ProjectFragment : Fragment(), OnStartDragListener {

    private lateinit var binding: FragmentProjectBinding
    private var projectsList : ArrayList<Projects> = ArrayList()
    private lateinit var resumeViewModel : ResumeViewModel

    private var mItemTouchHelper: ItemTouchHelper? = null
    private lateinit var callback  : SimpleItemTouchHelperCallback

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

        var projectItem = Projects("","","","", "", "", resumeId!!)
        projectsList.add(projectItem)


        var adapter = ProjectItemAdapter(context!!, projectsList, this)
        binding.rvProjDetails.adapter = adapter
        binding.rvProjDetails.layoutManager = LinearLayoutManager(context)

        callback = SimpleItemTouchHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(binding.rvProjDetails)

        adapter.notifyDataSetChanged()

        binding.tvAddProjSection.setOnClickListener {

            projectItem = Projects("","","","", "", "", resumeId)
            projectsList.add(projectItem)

            adapter = ProjectItemAdapter(context!!, projectsList, this)
            binding.rvProjDetails.adapter = adapter
            binding.rvProjDetails.layoutManager = LinearLayoutManager(context)

            callback = SimpleItemTouchHelperCallback(adapter)
            mItemTouchHelper = ItemTouchHelper(callback)
            mItemTouchHelper!!.attachToRecyclerView(binding.rvProjDetails)

            adapter.notifyDataSetChanged()
        }


        binding.btnSave.setOnClickListener {

            projectsList.forEach {
                resumeViewModel.insertProjectDetails(it)
            }

            val b  = Bundle()
            b.putInt("resumeId", resumeId)

            NavHostFragment.findNavController(this).navigate(R.id.action_projectFragment_to_saveFragment,b)
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