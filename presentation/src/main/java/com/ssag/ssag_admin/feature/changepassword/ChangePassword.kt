package com.ssag.ssag_admin.feature.changepassword

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ssag.ssag_admin.base.observeWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun ChangePassword(
    navController: NavController,
    changePasswordViewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val changePasswordState = changePasswordViewModel.state.collectAsState().value

    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {

        changePasswordViewModel.event.observeWithLifecycle {
            when (it) {
                is ChangePasswordViewModel.ChangePasswordEvent.ChangePasswordFail -> {
                    val changePasswordFailComment = "비밀번호 변경을 실패했습니다."
                    scaffoldState.snackbarHostState.showSnackbar(changePasswordFailComment)
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