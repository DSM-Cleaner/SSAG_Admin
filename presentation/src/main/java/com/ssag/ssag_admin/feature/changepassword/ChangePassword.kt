package com.ssag.ssag_admin.feature.changepassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ssag.ssag_admin.base.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChangePassword(
    navController: NavController,
    changePasswordViewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val changePasswordState = changePasswordViewModel.state.collectAsState().value

    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "비밀번호 변경") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back")
                    }
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                elevation = 12.dp
            )
        }
    ) {

        ChangePasswordContent(
            state = changePasswordState,
            doOnChangePasswordClick = {
                coroutineScope.launch {
                    changePasswordViewModel.changePassword()
                }
            },
            doOnCurrentPasswordInput = {
                changePasswordViewModel.inputCurrentPassword(it)
            },
            doOnNewPasswordInput = {
                changePasswordViewModel.inputNewPassword(it)
            },
            doOnConfirmPasswordInput = {
                changePasswordViewModel.inputConfirmPassword(it)
            }
        )

        changePasswordViewModel.event.observeWithLifecycle {
            when (it) {
                is ChangePasswordViewModel.ChangePasswordEvent.ChangePasswordFail -> {
                    val changePasswordFailComment = "비밀번호 변경을 실패했습니다."
                    scaffoldState.snackbarHostState.showSnackbar(changePasswordFailComment)
                }

                is ChangePasswordViewModel.ChangePasswordEvent.NotDoneInput -> {
                    val notDoneInputComment = "모든 정보를 입력해주세요"
                    scaffoldState.snackbarHostState.showSnackbar(notDoneInputComment)
                }

                is ChangePasswordViewModel.ChangePasswordEvent.ChangePasswordSuccess -> {
                    val successLoginComment = "비밀번호를 변경하였습니다."
                    scaffoldState.snackbarHostState.showSnackbar(successLoginComment)
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun ChangePasswordContent(
    state: ChangePasswordState,
    doOnChangePasswordClick: () -> Unit,
    doOnCurrentPasswordInput: (String) -> Unit,
    doOnNewPasswordInput: (String) -> Unit,
    doOnConfirmPasswordInput: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        val currentPasswordComment = "현재 비밀번호를 입력해주세요"
        ChangePasswordTextField(
            textValue = state.currentPassword,
            doOnValueChange = doOnCurrentPasswordInput,
            labelText = currentPasswordComment
        )

        val newPasswordComment = "새로운 비밀번호를 입력해주세요"
        ChangePasswordTextField(
            textValue = state.newPassword,
            doOnValueChange = doOnNewPasswordInput,
            labelText = newPasswordComment
        )

        ConfirmPasswordTextField(state = state, doOnConfirmPasswordInput = doOnConfirmPasswordInput)
    }
}

@Composable
fun ChangePasswordTextField(
    textValue: String,
    doOnValueChange: (String) -> Unit,
    labelText: String
) {
    OutlinedTextField(
        value = textValue,
        onValueChange = doOnValueChange,
        label = {
            Text(text = labelText)
        }
    )
}

@Composable
fun ConfirmPasswordTextField(
    state: ChangePasswordState,
    doOnConfirmPasswordInput: (String) -> Unit
) {
    val confirmPasswordText = "새로운 비밀번호를 한번 더 입력해주세요"
    OutlinedTextField(
        value = state.confirmPassword,
        onValueChange = doOnConfirmPasswordInput,
        label = {
            Text(
                text = confirmPasswordText
            )
        },
        isError = state.passwordIsDifferent
    )
}

@Preview
@Composable
fun ChangePasswordContentPreview() {
    ChangePasswordContent(
        state = ChangePasswordState.initial(),
        doOnConfirmPasswordInput = {},
        doOnNewPasswordInput = {},
        doOnCurrentPasswordInput = {},
        doOnChangePasswordClick = {}
    )
}