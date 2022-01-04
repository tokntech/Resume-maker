package com.nikitha.toknresumebuilder.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikitha.toknresumebuilder.database.relations.PersonalAndEducational
import com.nikitha.toknresumebuilder.model.*

@Dao
interface ResumeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonalDetails(personalDetails: PersonalDetails) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEducationDetails(educationalDetails: EducationalDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjectDetails(project: Projects)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkillDetails(skills: Skill)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfessionalDetails(professionalDetails: ProfessionalDetails)

    @Update(entity= PersonalDetails::class)
    suspend fun updateObjective(objective: Objective)

    @Query("Select * from PersonalDetails where resume_id=:resume_id")
    suspend fun getEducationDetails(resume_id:Int): List<PersonalAndEducational>


}