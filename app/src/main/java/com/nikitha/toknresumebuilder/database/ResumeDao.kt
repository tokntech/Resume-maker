package com.nikitha.toknresumebuilder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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

    @Query("Select * from PersonalDetails where resume_id=:resume_id")
    suspend fun getEducationDetails(resume_id:Int): List<PersonalAndEducational>


}