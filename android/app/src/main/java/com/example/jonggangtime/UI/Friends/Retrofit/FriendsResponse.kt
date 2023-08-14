package com.example.jonggangtime.UI.Friends.Retrofit

//추후 api 개발에 따라 변경 예정 - 친구 리스트 조회 결과
data class Friend(
    var friendId: Int,
    var friendName: String? = ""
)