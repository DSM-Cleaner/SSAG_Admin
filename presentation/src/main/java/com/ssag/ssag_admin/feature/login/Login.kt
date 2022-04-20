package com.ssag.ssag_admin.feature.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val loginState = loginViewModel.state.collectAsState().value
    LoginContent(loginState = loginState)
}

@Composable
fun LoginContent(loginState: LoginState) {

}