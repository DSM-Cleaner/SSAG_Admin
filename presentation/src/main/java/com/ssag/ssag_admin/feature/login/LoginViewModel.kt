package com.ssag.ssag_admin.feature.login

import com.ssag.domain.auth.usecase.LoginUseCase
import com.ssag.domain.auth.usecase.LogoutUseCase
import com.ssag.ssag_admin.base.BaseViewModel
import com.ssag.ssag_admin.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
) : BaseViewModel<LoginState, LoginIntent, LoginViewModel.LoginEvent>() {

    override val initialState: LoginState
        get() = LoginState.initial()

    suspend fun login() {
        kotlin.runCatching {
            sendIntent(LoginIntent.StartLoading)
            loginUseCase.execute(state.value.password)
        }.onSuccess {
            sendIntent(LoginIntent.SuccessLogin(it))
        }.onFailure {
            sendEvent(LoginEvent.FailedLogin)
        }.also {
            sendIntent(LoginIntent.FinishLoading)
        }
    }

    fun inputPassword(password: String) {
        sendIntent(LoginIntent.InputPassword(password))
    }

    suspend fun logout() {
        logoutUseCase.execute(Unit)
        sendIntent(LoginIntent.Logout)
    }

    override fun reduceIntent(oldState: LoginState, intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SuccessLogin -> {
                setState(
                    oldState.copy(
                        hasLogin = true,
                        teacherName = intent.teacherEntity.name,
                        startFloor = if (intent.teacherEntity.isManTeacher) 5 else 2
                    )
                )
            }

            is LoginIntent.InputPassword -> {
                setState(
                    oldState.copy(
                        password = intent.password
                    )
                )
            }

            is LoginIntent.Logout -> {
                setState(
                    oldState.copy(
                        hasLogin = false,
                        teacherName = "",
                        startFloor = 0
                    )
                )
            }

            is LoginIntent.StartLoading -> {
                setState(
                    oldState.copy(
                        isLoading = true
                    )
                )
            }

            is LoginIntent.FinishLoading -> {
                setState(
                    oldState.copy(
                        isLoading = false
                    )
                )
            }
        }
    }

    sealed class LoginEvent : Event {
        object FailedLogin : LoginEvent()
    }
}