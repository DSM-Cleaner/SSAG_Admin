package com.ssag.domain.clean.entity

enum class PersonalPlaceEntity {
    PASS,
    DID_NOT_PASS,
    NOT_CHECK_PERSONAL_DAY
}

fun PersonalPlaceEntity.isComplete() =
    when(this) {
        PersonalPlaceEntity.PASS -> false
        PersonalPlaceEntity.DID_NOT_PASS -> true
        PersonalPlaceEntity.NOT_CHECK_PERSONAL_DAY -> null
    }