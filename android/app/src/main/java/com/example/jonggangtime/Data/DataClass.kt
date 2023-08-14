package com.example.jonggangtime.Data

data class LectureData(
    val lectureName: String,
    val professorName: String,
    val category: Int,
    val nums: Int,
    val shortContent: String
)

data class DetailLectureData(
    val lectureName: String,
    val professorName: String,
    val category: Int,
    val nums: Int,
    val shortContent: String,
    val Content: String
)
