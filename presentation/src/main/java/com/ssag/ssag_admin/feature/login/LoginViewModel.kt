package com.ssag.ssag_admin.feature.login

import androidx.lifecycle.ViewModel
import com.ssag.domain.feature.auth.entity.TeacherEntity
import com.ssag.domain.feature.auth.parameter.LoginParameter
import com.ssag.domain.feature.auth.usecase.CheckNeedLoginUseCase
import com.ssag.domain.feature.auth.usecase.LoginUseCase
import com.ssag.domain.feature.auth.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val checkNeedLoginUseCase: CheckNeedLoginUseCase,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {

    override val container: Container<LoginState, LoginSideEffect> = container(LoginState.initial())

    fun checkNeedLogin() = intent {
        kotlin.runCatching {
            checkNeedLoginUseCase.execute(Unit)
        }.onSuccess {
            it.setTeacher()
        }.onFailure {
            postSideEffect(LoginSideEffect.FailedAutoLogin)
        }
    }

    fun login() = intent {
        if (!state.isLoading) {
            kotlin.runCatching {
                startLoading()
                val parameter = LoginParameter(state.name, state.password)
                loginUseCase.execute(parameter)
            }.onSuccess {
                it.setTeacher()
            }.onFailure {
                postSideEffect(LoginSideEffect.FailedLogin)
            }.also {
                finishLoading()
            }
        }
    }

    private fun TeacherEntity.setTeacher() = intent {
        reduce {
            state.copy(
                hasLogin = true,
                teacherName = this@setTeacher.name,
                startFloor = if (this@setTeacher.isManTeacher) 5 else 2
            )
        }
    }

    private fun startLoading() = intent {
        reduce { state.copy(isLoading = true) }
    }

    private fun finishLoading() = intent {
        reduce { state.copy(isLoading = false) }
    }

    fun inputName(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    fun inputPassword(password: String) = intent {
        reduce { state.copy(password = password) }
    }

    suspend fun logout() = intent {
        logoutUseCase.execute(Unit)
        reduce { state.copy(hasLogin = false, teacherName = "", startFloor = 0) }
    }
}