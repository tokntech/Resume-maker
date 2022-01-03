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
    ]
)
data class Skill(
    var skill_name: String = "",
    var isStarOrRatingBar: String = "",
    var rating: Int = 1,
    var resume_id:Int,
    @PrimaryKey(autoGenerate = true)
    var skill_id :Int  = 0
)