package com.ssag.domain.auth.usecase

import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<String, Unit>(){

    override suspend fun execute(data: String) {
        authRepository.changePassword(data)
    }
}