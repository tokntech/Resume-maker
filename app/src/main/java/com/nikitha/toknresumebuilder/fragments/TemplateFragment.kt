package com.nikitha.toknresumebuilder.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.adapter.TemplateAdapter
import com.nikitha.toknresumebuilder.databinding.FragmentTemplateBinding
import com.nikitha.toknresumebuilder.model.TemplateImages

class TemplateFragment : Fragment() {
private lateinit var binding: FragmentTemplateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTemplateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val colorDrawable = ColorDrawable(Color.TRANSPARENT)
        (activity as? AppCompatActivity)?.supportActionBar?.setBackgroundDrawable(colorDrawable)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.back_arrow)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbar  = activity?.findViewById<Toolbar>(R.id.holder_toolbar)
        toolbar?.findViewById<ImageView>(R.id.ivtips)?.visibility = View.INVISIBLE
        toolbar?.findViewById<ImageView>(R.id.ivPreview)?.visibility = View.INVISIBLE

        val templateImages = TemplateImages(R.drawable.ic_rectangle)
        val adapter = TemplateAdapter(templateImages)
        binding.rvTemplateImages.adapter = adapter
        binding.rvTemplateImages.layoutManager = LinearLayoutManager(activity)
        adapter.notifyDataSetChanged()

        binding.btnNext.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_templateFragment_to_sectionsFragment)
        }
        setHasOptionsMenu(true)
    }

    //Hide the preview and blogs button
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }
}