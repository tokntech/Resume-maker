package com.nikitha.toknresumebuilder.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nikitha.toknresumebuilder.database.ResumeDatabase
import com.nikitha.toknresumebuilder.database.ResumeRepository
import com.nikitha.toknresumebuilder.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ResumeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ResumeRepository
    private val tag = "ResumeViewModel"
    private  var resumeId = 0L

    init {
        val resumeDb = ResumeDatabase.getInstance(application).dao
        repository = ResumeRepository(resumeDb)

    }

    fun insertPersonalDetails(personalDetails: PersonalDetails): Long {
            runBlocking {
                resumeId = withContext(Dispatchers.IO) { repository.insertPersonalDetails(personalDetails) }
                Log.d(tag, "Inserted row inside with context is $resumeId")
            }
        Log.d(tag, "Inserted row is $resumeId")
        return resumeId
    }

    fun insertEducationalDetails(educationalDetails: EducationalDetails) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertEducationDetails(educationalDetails)
        }

    fun insertProfessionalDetails(professionalDetails: ProfessionalDetails) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProfessionalDetails(professionalDetails)
        }

    fun insertSkillDetails(skill: Skill) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertSkillDetails(skill)
        }

    fun insertProjectDetails(projects: Projects) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProjectDetails(projects)
        }

    fun updateObjective(objective: Objective) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateObjective(objective)
        }

}