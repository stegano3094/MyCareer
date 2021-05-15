package com.stegano.mycareer

// career.json 파일을 위한 데이터 클래스 (MainActivity)
data class CareerJsonSet(
    val date: String,
    val content: String
)

data class CareerGsonSet(
    val my_career: ArrayList<CareerJsonSet>
)


// project.json 파일을 위한 데이터 클래스 (MainActivity)
data class ProjectJsonSet(
    val projectName: String,
    val developmentPeriod: String,
    val developmentPersonnel: String,
    val summary: String,
    val skill: String,
    val imgUri: String
)

data class ProjectGsonSet(
    val project: ArrayList<ProjectJsonSet>
)