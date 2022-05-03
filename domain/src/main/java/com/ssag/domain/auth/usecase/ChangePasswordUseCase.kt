package com.ssag.domain.auth.usecase

import com.ssag.domain.auth.parameter.ChangePasswordParameter
import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<ChangePasswordParameter, Unit>(){

    override suspend fun execute(data: ChangePasswordParameter) {
        authRepository.changePassword(data)
    }
}