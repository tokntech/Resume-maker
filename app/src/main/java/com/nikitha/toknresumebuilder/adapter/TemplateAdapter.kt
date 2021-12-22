package com.nikitha.toknresumebuilder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.TemplateImages

class TemplateAdapter(private val templateImages: TemplateImages) : RecyclerView.Adapter<TemplateAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var ivTemplateImage: ImageView= itemView.findViewById(R.id.ivTemplateImage)


        fun bind(position: Int)
        {


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_template_item, parent, false)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.ivTemplateImage.setImageResource(templateImages.imageSrc)
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 1
    }

}