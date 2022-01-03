package com.nikitha.toknresumebuilder.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.nikitha.toknresumebuilder.R
import com.nikitha.toknresumebuilder.model.Projects

class ProjectItemAdapter(private val projectList: ArrayList<Projects>): RecyclerView.Adapter<ProjectItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            etProjectTitle?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    projectList[position].project_title = etProjectTitle.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProjectDuration?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    projectList[position].start_date = etProjectDuration.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })

            etProjectDesc?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    projectList[position].description = etProjectDesc.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
            etProjectLink?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    projectList[position].link = etProjectLink.text.toString()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            })
        }

        val etProjectTitle : EditText? = itemView.findViewById(R.id.etTitle)
        val etProjectDuration : EditText? = itemView.findViewById(R.id.etProjDuration)
        val etProjectDesc : EditText? = itemView.findViewById(R.id.etProjDesc)
        val etProjectLink : EditText? = itemView.findViewById(R.id.etProjLink)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProjectItemAdapter.ViewHolder {
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_project_item, parent, false)))
    }

    override fun onBindViewHolder(holder: ProjectItemAdapter.ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }


}