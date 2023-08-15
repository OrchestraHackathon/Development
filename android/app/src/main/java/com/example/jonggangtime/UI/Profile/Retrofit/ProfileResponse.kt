package com.example.jonggangtime.UI.Profile.Retrofit

//추후 api 개발에 따라 변경 예정 - 수강완료 과목 리스트 조회 결과
data class CompletedLecture(
    var courseId: Int,
    var courseName: String? = "",
    var professor: String? = "",
    var categoryName: String? = "",
    var courseDetails: String? = "",
    var registerPeople: String? = "",
    var flag : Boolean
)