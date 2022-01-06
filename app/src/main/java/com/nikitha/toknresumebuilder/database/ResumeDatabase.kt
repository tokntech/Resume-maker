package com.nikitha.toknresumebuilder.database

import android.content.Context
import androidx.room.*
import com.nikitha.toknresumebuilder.model.EducationalDetails
import com.nikitha.toknresumebuilder.model.PersonalDetails
import com.nikitha.toknresumebuilder.model.ProfessionalDetails
import com.nikitha.toknresumebuilder.model.Projects
import com.nikitha.toknresumebuilder.model.Skill

@Database(
    entities =
    [
        PersonalDetails::class,
        EducationalDetails::class,
        ProfessionalDetails::class,
        Projects::class,
        Skill::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converter::class)
abstract class ResumeDatabase: RoomDatabase()
{
    abstract val dao: ResumeDao

    companion object{

        @Volatile
        private var INSTANCE : ResumeDatabase? = null

        fun getInstance(context: Context): ResumeDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ResumeDatabase::class.java,
                    "resume_db2")
                    .build()
            }
        }
    }

}
