package com.ssag.domain.clean.parameter

import com.ssag.domain.clean.entity.StudentEntity

data class PostCleanStateParameter(
    val roomId: Int,
    val lightIsNotComplete: Boolean,
    val plugIsNotComplete: Boolean,
    val shoesAreNotComplete: Boolean,
    val studentList: List<StudentEntity>
)