package com.stegano.mycareer

data class JsonDataSetting(
    val name: String,
    val packageName: String,
    val logoImgUri: String,
    val comment: String
)

data class GsonDataSetting(
    val sample_project: ArrayList<JsonDataSetting>
)