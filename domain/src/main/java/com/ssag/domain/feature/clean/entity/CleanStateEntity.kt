package com.ssag.domain.feature.clean.entity

data class CleanStateEntity(
    val beddingIsNotClean: Boolean,
    val clotheIsNotClean: Boolean,
    val personalPlaceIsNotClean: Boolean?
)