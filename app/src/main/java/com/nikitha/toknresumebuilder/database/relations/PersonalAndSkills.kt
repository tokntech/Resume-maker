package com.nikitha.toknresumebuilder.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.nikitha.toknresumebuilder.model.PersonalDetails
import com.nikitha.toknresumebuilder.model.Skill

data class PersonalAndSkills(
    @Embedded
    val personalDetails: PersonalDetails,

    @Relation(
        parentColumn = "resume_id",
        entityColumn = "resume_id"
    )
    val skills: List<Skill>
)