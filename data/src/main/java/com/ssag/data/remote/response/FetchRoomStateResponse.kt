package com.ssag.data.remote.response

import com.google.gson.annotations.SerializedName

data class FetchRoomStateResponse(
    @SerializedName("light") val light: Boolean,
    @SerializedName("plug") val plug: Boolean,
    @SerializedName("shoes") val shoes: Boolean,
    @SerializedName("student_list") val studentList: List<StudentResponse>
) {

    data class StudentResponse(
        @SerializedName("bed") val bed: String,
        @SerializedName("gcn") val gcn: Int,
        @SerializedName("name") val name: String,
        @SerializedName("bedding") val bedding: Int,
        @SerializedName("clothes") val clothes: Int,
        @SerializedName("personal_place") val personalPlace: Boolean?
    )
}