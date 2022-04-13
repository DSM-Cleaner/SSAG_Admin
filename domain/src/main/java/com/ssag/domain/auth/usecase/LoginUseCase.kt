package com.ssag.domain.auth.usecase

import com.ssag.domain.auth.entity.TeacherEntity
import com.ssag.domain.auth.repository.AuthRepository
import com.ssag.domain.base.UseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<String, TeacherEntity>(){

    override suspend fun execute(data: String): TeacherEntity =
        authRepository.login(data)
}