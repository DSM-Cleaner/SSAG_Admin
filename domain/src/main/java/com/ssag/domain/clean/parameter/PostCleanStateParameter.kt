package com.ssag.domain.clean.parameter

import com.ssag.domain.clean.entity.StudentEntity

data class PostCleanStateParameter(
    val roomId: Int,
    val lightIsComplete: Boolean,
    val plugIsComplete: Boolean,
    val shoesAreComplete: Boolean,
    val studentList: List<StudentEntity>
)