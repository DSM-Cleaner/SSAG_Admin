package com.ssag.domain.feature.auth.usecase

import com.ssag.domain.feature.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import com.ssag.domain.feature.auth.entity.TeacherEntity
import javax.inject.Inject

class CheckNeedLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, TeacherEntity>() {

    override suspend fun execute(data: Unit): TeacherEntity =
        authRepository.checkNeedLogin()
}