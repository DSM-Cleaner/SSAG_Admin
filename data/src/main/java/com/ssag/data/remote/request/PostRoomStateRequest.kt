package com.ssag.data.remote.request

import com.google.gson.annotations.SerializedName

data class PostRoomStateRequest(
    @SerializedName("light") val light: Boolean,
    @SerializedName("plug") val plug: Boolean,
    @SerializedName("shoes") val shoes: Boolean,
    @SerializedName("student_list") val studentList: List<StudentRequest>
) {

    data class StudentRequest(
        @SerializedName("id") val id: Long,
        @SerializedName("bedding") val bedding: Int,
        @SerializedName("clothes") val clothes: Int,
        @SerializedName("personal_place") val personalPlace: Boolean?
    )
}