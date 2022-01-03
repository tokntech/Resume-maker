package com.nikitha.toknresumebuilder.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nikitha.toknresumebuilder.model.EducationalDetails
import com.nikitha.toknresumebuilder.model.PersonalDetails

data class PersonalAndEducational(
    @Embedded
    val personalDetails: PersonalDetails,

    @Relation(
        parentColumn = "resume_id",
        entityColumn = "resume_id"
    )
    val educationalDetails: List<EducationalDetails>
)