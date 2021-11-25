package com.nikitha.toknresumebuilder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalDetails( val name:String,
                            val jobTitle:String,
                            val phoneNumber:String,
                            val email:String,
                            val website:String,
                            val linkedInUrl: String
                           ) : Parcelable