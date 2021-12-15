package com.nikitha.toknresumebuilder.model

data class ProfessionalDetails(
    var comp_name: String,
    var work_location: String,
    var comp_description: String,
    var designation: ArrayList<Designation>
    )