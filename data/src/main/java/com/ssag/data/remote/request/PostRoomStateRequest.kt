package com.ssag.data.remote.request

import com.google.gson.annotations.SerializedName
import com.ssag.data.toInt
import com.ssag.domain.clean.entity.StudentEntity
import com.ssag.domain.clean.parameter.PostCleanStateParameter

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

fun PostCleanStateParameter.toRequest() =
    PostRoomStateRequest(
        light = lightIsNotComplete,
        plug = plugIsNotComplete,
        shoes = shoesAreNotComplete,
        studentList = studentList.toRequest()
    )

fun List<StudentEntity>.toRequest() =
    this.map { it.toRequest() }

fun StudentEntity.toRequest() =
    PostRoomStateRequest.StudentRequest(
        id = id,
        bedding = cleanState.beddingIsNotClean.toInt(),
        clothes = cleanState.clotheIsNotClean.toInt(),
        personalPlace = cleanState.personalPlaceIsNotClean
    )