package com.ssag.data.remote.request

import com.google.gson.annotations.SerializedName
import com.ssag.data.toInt
import com.ssag.domain.feature.clean.entity.StudentEntity
import com.ssag.domain.feature.clean.parameter.PostCleanStateParameter
import org.threeten.bp.LocalDate

data class PostRoomStateRequest(
    @SerializedName("day") val day: String,
    @SerializedName("light") val light: Boolean,
    @SerializedName("plug") val plug: Boolean,
    @SerializedName("shoes") val shoes: Boolean,
    @SerializedName("student_list") val studentList: List<StudentRequest>
) {

    data class StudentRequest(
        @SerializedName("user_id") val id: Long,
        @SerializedName("day") val day: String,
        @SerializedName("bedding") val bedding: Int,
        @SerializedName("clothes") val clothes: Int,
        @SerializedName("personal_place") val personalPlace: Boolean?
    )
}

fun PostCleanStateParameter.toRequest() =
    PostRoomStateRequest(
        light = lightIsNotComplete,
        plug = plugIsNotComplete,
        shoes = shoesAreNotComplete,
        studentList = studentList.toRequest(),
        day = LocalDate.now().dayOfWeek.name
    )

fun List<StudentEntity>.toRequest() =
    this.map { it.toRequest() }

fun StudentEntity.toRequest() =
    PostRoomStateRequest.StudentRequest(
        id = id,
        day = LocalDate.now().dayOfWeek.name,
        bedding = cleanState.beddingIsNotClean.toInt(),
        clothes = cleanState.clotheIsNotClean.toInt(),
        personalPlace = cleanState.personalPlaceIsNotClean
    )