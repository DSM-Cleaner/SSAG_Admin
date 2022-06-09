package com.ssag.data.remote.response

import com.google.gson.annotations.SerializedName
import com.ssag.data.toBoolean
import com.ssag.domain.feature.clean.entity.CleanStateEntity
import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.entity.StudentEntity

data class FetchRoomStateResponse(
    @SerializedName("light") val light: Boolean,
    @SerializedName("plug") val plug: Boolean,
    @SerializedName("shoes") val shoes: Boolean,
    @SerializedName("student_list") val studentList: List<StudentResponse>
) {

    data class StudentResponse(
        @SerializedName("id") val id: Long,
        @SerializedName("bed") val bed: String,
        @SerializedName("user_id") val gcn: Int,
        @SerializedName("name") val name: String,
        @SerializedName("bedding") val bedding: Int,
        @SerializedName("clothes") val clothes: Int,
        @SerializedName("personal_place") val personalPlace: Boolean?
    ) {
        fun toEntity() =
            StudentEntity(
                id = id,
                bedPosition = bed,
                gcn = gcn,
                name = name,
                cleanState = CleanStateEntity(
                    beddingIsNotClean = bedding.toBoolean(),
                    clotheIsNotClean = clothes.toBoolean(),
                    personalPlaceIsNotClean = personalPlace
                )
            )
    }

    fun toEntity() =
        RoomStateEntity(
            lightIsNotComplete = light,
            plugIsNotComplete = plug,
            shoesAreNotComplete = shoes,
            students = studentList.map { it.toEntity() }
        )
}