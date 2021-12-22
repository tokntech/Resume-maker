package com.nikitha.toknresumebuilder.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "PersonalDetails")
data class PersonalDetails( val name:String,
                            val jobTitle:String,
                            val phoneNumber:String,
                            val email:String,
                            val website:String,
                            val linkedInUrl: String,
                            @PrimaryKey(autoGenerate = false) val id: Int? = null) : Parcelable