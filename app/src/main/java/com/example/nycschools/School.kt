package com.example.nycschools

import com.fasterxml.jackson.annotation.JsonIgnoreProperties


data class SchoolSatDetails(
    val dbn: String? = null,
    val school_name: String? = null,
    val num_of_sat_test_takers : String? = null,
    val sat_critical_reading_avg_score: String? = null,
    val sat_math_avg_score: String? = null,
    val sat_writing_avg_score: String? = null,
    var schoolNotFound: Boolean? =  false
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class School(
    val dbn: String? = null,
    val school_name: String?,
    val academicopportunities1: String? = null,
    val academicopportunities2: String? = null,
    val location: String? = null,
    val phone_number: String? = null,
    val requirement1_1: String? = null,
    val website: String? = null,
)


