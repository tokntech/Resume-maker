package com.nikitha.toknresumebuilder.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.Slider
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.Skill

class SkillItemAdapter(private val skillsItem: ArrayList<Skill>, val selectedRating: String) : RecyclerView.Adapter<SkillItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var etSkillName: EditText? = itemView.findViewById(R.id.etSkillItem)
        var ratingBar :RatingBar? = itemView.findViewById(R.id.ratingBar)
        var slider:Slider? = itemView.findViewById(R.id.ratingSlider)


        fun bind(position: Int)
        {

            etSkillName?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    skillsItem[position].skill_name = etSkillName!!.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_skill_item, parent, false)))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etSkillName?.setText(skillsItem[position].skill_name)

        if(selectedRating == "star") {
            holder.ratingBar?.visibility = View.VISIBLE
            holder.slider?.visibility = View.INVISIBLE
        }
        else if(selectedRating == "progressBar")
        {
            holder.ratingBar?.visibility = View.INVISIBLE
            holder.slider?.visibility = View.VISIBLE
        }
        else
        {
            holder.ratingBar?.visibility = View.GONE
            holder.slider?.visibility = View.GONE
        }



        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return skillsItem.size
    }

    /*override fun onRatingSelected() {

    }*/
}