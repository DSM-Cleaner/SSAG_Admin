package com.ssag.domain.clean.parameter

import com.ssag.domain.clean.entity.CleanStateEntity

data class PostCleanStateParameter(
    val roomId: Int,
    val lightIsComplete: Boolean,
    val plusIsComplete: Boolean,
    val shoesAreComplete: Boolean,
    val studentA: CleanStateEntity,
    val studentB: CleanStateEntity,
    val studentC: CleanStateEntity
)