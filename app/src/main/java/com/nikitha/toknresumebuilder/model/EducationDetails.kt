package com.nikitha.toknresumebuilder.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(entity = PersonalDetails::class,
            parentColumns = ["resume_id"],
            childColumns = ["resume_id"],
            onDelete = ForeignKey.CASCADE)
    ])
data class EducationalDetails(
    var  school: String = "",
    var location: String = "",
    var course: String = "",
    var start_year: String = "",
    var end_year: String = "",
    var key_achievements: String = "",
    var resume_id:Int,

    @PrimaryKey(autoGenerate = true)
    var edu_id: Int =0
)