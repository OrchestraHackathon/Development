package com.example.jonggangtime.Data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LoginInfo(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String
)

data class SigninInfo(
    @SerializedName("email") var email : String,
    @SerializedName("password") var password : String,
    @SerializedName("userName") var name : String,
    @SerializedName("userNickName") var nickname: String
)

data class LectureData(
    @SerializedName("courseId") val lectureId: Int,
    @SerializedName("courseName") val lectureName: String,
    @SerializedName("professor") val professorName: String,
    @SerializedName("categoryName") val category: String,
    @SerializedName("registerPeople") val nums: Int,
    @SerializedName("courseSummary") val shortContent: String
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readString().toString()
    )

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(lectureId)
        dest.writeString(lectureName)
        dest.writeString(professorName)
        dest.writeString(category)
        dest.writeInt(nums)
        dest.writeString(shortContent)
    }

    companion object CREATOR : Parcelable.Creator<LectureData> {
        override fun createFromParcel(parcel: Parcel): LectureData {
            return LectureData(parcel)
        }

        override fun newArray(size: Int): Array<LectureData?> {
            return arrayOfNulls(size)
        }
    }

}

data class DetailLectureData(
    val lectureName: String,
    val professorName: String,
    val category: Int,
    val nums: Int,
    val shortContent: String,
    val Content: String
)

data class SeekFriendData(
    val nickname: String,
    val name: String
)

data class LectureCategoryData(
    val categoryId : Int,
    val categoryName : String
)

data class RegistLectureData(
    val courseName: String,
    val categories: ArrayList<Int>,
    val courseSummary: String,
    val courseDetail: String
)
//ArrayList<Int>인지 ArrayList<Object>인지? -> Int 값으로 보내도 상관 없다!