package com.ssag.ssag_admin.feature.login

import com.ssag.domain.auth.usecase.LoginUseCase
import com.ssag.ssag_admin.base.BaseViewModel
import com.ssag.ssag_admin.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginIntent, LoginViewModel.LoginEvent>() {

    override val initialState: LoginState
        get() = LoginState.initial()

    suspend fun login() {
        kotlin.runCatching {
            loginUseCase.execute(state.value.password)
        }.onSuccess {
            sendEvent(LoginEvent.SuccessLogin)
        }.onFailure {
            sendEvent(LoginEvent.FailedLogin)
        }
    }

    fun inputPassword(password: String) {
        sendIntent(LoginIntent.InputPassword(password))
    }

    override fun reduceIntent(oldState: LoginState, intent: LoginIntent) {
        when (intent) {
            is LoginIntent.SetTeacher -> {
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
        }
    }

    sealed class LoginEvent : Event {
        object SuccessLogin : LoginEvent()
        object FailedLogin : LoginEvent()
    }
}