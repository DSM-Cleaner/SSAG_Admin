package com.ssag.domain.clean.repository

import com.ssag.domain.clean.parameter.PostCleanStateParameter

interface CleanRepository {

    fun postCleanState(postCleanStateParameter: PostCleanStateParameter)
}