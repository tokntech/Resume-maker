package com.nikitha.toknresumebuilder.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = PersonalDetails::class,
        parentColumns = ["resume_id"],
        childColumns = ["resume_id"],
        onDelete = ForeignKey.CASCADE)
])
data class Projects(
    var project_title: String ="",
    var start_date: String = "",
    var end_date: String = "",
    var description: String = "",
    var link:String = "",
    var resume_id:Int,

    @PrimaryKey(autoGenerate = true)
    var proj_id:Int =0
)