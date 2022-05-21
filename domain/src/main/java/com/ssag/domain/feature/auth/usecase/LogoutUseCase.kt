package com.ssag.domain.feature.auth.usecase

import com.ssag.domain.feature.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit) {
        authRepository.logout()
    }
}