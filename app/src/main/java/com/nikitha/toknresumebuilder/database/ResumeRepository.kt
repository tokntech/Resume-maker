package com.nikitha.toknresumebuilder.database

import androidx.lifecycle.LiveData
import com.nikitha.toknresumebuilder.model.*

class ResumeRepository(private var dao: ResumeDao) {

    suspend fun insertPersonalDetails(personalDetails: PersonalDetails) :Long {
        return dao.insertPersonalDetails(personalDetails)
    }

    suspend fun insertEducationDetails(educationalDetails: EducationalDetails) = dao.insertEducationDetails(educationalDetails)

    suspend fun insertProfessionalDetails(professionalDetails: ProfessionalDetails) = dao.insertProfessionalDetails(professionalDetails)

    suspend fun insertProjectDetails(project: Projects) = dao.insertProjectDetails(project)

    suspend fun insertSkillDetails(skills: Skill) = dao.insertSkillDetails(skills)

    suspend fun updateObjective(objective: Objective) = dao.updateObjective(objective)
}