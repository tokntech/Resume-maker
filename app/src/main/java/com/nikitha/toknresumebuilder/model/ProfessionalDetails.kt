package com.nikitha.toknresumebuilder.model

data class ProfessionalDetails(
    var profile: String,
    var organization: String,
    var grade: String,
    val wfh:String,
    var work_location: String,
    var work_Startyear: String,
    var work_EndYear: String,
    var job_description: String)