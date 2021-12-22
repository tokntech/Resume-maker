package com.nikitha.toknresumebuilder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R

class ResumeItemAdapter :RecyclerView.Adapter<ResumeItemAdapter.ViewHolder>() {
   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeItemAdapter.ViewHolder {
      return  (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_resume_item, parent, false)))
    }

    override fun onBindViewHolder(holder: ResumeItemAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}