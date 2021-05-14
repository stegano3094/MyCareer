package com.stegano.mycareer

// career.json 파일을 위한 데이터 클래스
data class CareerJsonSet(
    val date: String,
    val content: String
)

data class CareerGsonSet(
    val my_career: ArrayList<CareerJsonSet>
)


// sampling_project.json 파일을 위한 데이터 클래스
data class ProjectJsonSet(
    val projectName: String,
    val developmentPeriod: String,
    val developmentPersonnel: String,
    val summary: String,
    val task: String,
    val comment: String
)

data class ProjectGsonSet(
    val sample_project: ArrayList<ProjectJsonSet>
)