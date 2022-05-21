package com.ssag.domain.feature.auth.usecase

import com.ssag.domain.feature.auth.parameter.ChangePasswordParameter
import com.ssag.domain.feature.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<ChangePasswordParameter, Unit>(){

    override suspend fun execute(data: ChangePasswordParameter) {
        authRepository.changePassword(data)
    }
}