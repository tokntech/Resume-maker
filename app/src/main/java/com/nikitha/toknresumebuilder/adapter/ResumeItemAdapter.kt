package com.nikitha.toknresumebuilder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R

class ResumeItemAdapter :RecyclerView.Adapter<ResumeItemAdapter.ViewHolder>() {
   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val ivOptions = itemView.findViewById<ImageView>(R.id.ivOptions)
       val ivEdit = itemView.findViewById<ImageView>(R.id.ivEdit)
       val ivShare = itemView.findViewById<ImageView>(R.id.ivShare)
       val ivDownload = itemView.findViewById<ImageView>(R.id.ivDownload)

       fun bind(position: Int)
       {

       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeItemAdapter.ViewHolder {
      return  (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_resume_item, parent, false)))
    }

    override fun onBindViewHolder(holder: ResumeItemAdapter.ViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}