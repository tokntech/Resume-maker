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
data class ProfessionalDetails(
    var company_name:String ="",
    var location: String = "",
    var description: String = "",
    var designation: List<Designation>,
    var resume_id:Int,

    @PrimaryKey(autoGenerate = true)
    var prof_id :Int =0
)