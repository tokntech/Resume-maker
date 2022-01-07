package com.nikitha.toknresumebuilder.model

data class Resume(val personalDetails: PersonalDetails, val educationalDetails: EducationalDetails,
                val professionalDetails: ProfessionalDetails, val skill: Skill,
                val projects: Projects)