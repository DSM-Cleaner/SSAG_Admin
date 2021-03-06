package com.ssag.domain.feature.clean.usecase

import com.ssag.domain.base.UseCase
import com.ssag.domain.feature.clean.parameter.PostCleanStateParameter
import com.ssag.domain.feature.clean.repository.CleanRepository
import javax.inject.Inject

class PostCleanStateUseCase @Inject constructor(
    private val cleanRepository: CleanRepository
) : UseCase<PostCleanStateParameter, Unit>() {

    override suspend fun execute(data: PostCleanStateParameter) {
        cleanRepository.postCleanState(data)
    }
}