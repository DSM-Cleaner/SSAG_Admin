package com.ssag.domain.clean.entity

data class CleanStateEntity(
    val beddingIsNotClean: Boolean,
    val clotheIsNotClean: Boolean,
    val personalPlaceIsNotClean: Boolean?
)