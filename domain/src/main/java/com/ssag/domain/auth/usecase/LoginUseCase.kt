package com.ssag.domain.auth.usecase

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.parameter.LoginParameter
import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<LoginParameter, TeacherEntity>(){

    override suspend fun execute(data: LoginParameter): TeacherEntity =
        authRepository.login(data)
}