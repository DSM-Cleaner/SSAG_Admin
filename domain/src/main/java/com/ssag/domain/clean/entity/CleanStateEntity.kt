package com.ssag.domain.clean.entity

data class CleanStateEntity(
    val beddingIsClean: Boolean,
    val clotheIsClean: Boolean,
    val personalPlaceIsClean: Boolean?
)