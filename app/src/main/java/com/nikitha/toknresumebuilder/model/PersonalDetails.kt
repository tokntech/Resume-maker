package com.nikitha.toknresumebuilder.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PersonalDetails(
    @PrimaryKey(autoGenerate = true)
    var resume_id: Int = 0,
    var full_name:String = "",
    var job_title:String = "",
    var address: String = "",
    var phone_number: String = "",
    var email:String = "",
    var objective:String = "",
    var linkedinText:String = "",
    var linkedInUrl: String = "",
    var profilePhotoPath: String ="",
    @NonNull
    var extraSection: String = ""

) : Parcelable