package com.ssag.domain.auth.usecase

import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class CheckNeedLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit) {
        authRepository.checkNeedLogin()
    }
}