package com.stegano.mycareer

data class JsonDataSetting(
    val projectName: String,
    val developmentPeriod: String,
    val developmentPersonnel: String,
    val summary: String,
    val task: String,
    val comment: String
)

data class GsonDataSetting(
    val sample_project: ArrayList<JsonDataSetting>
)