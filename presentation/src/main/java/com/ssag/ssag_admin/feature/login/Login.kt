package com.ssag.ssag_admin.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ssag.ssag_admin.R
import com.ssag.ssag_admin.ui.theme.Blue700
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val loginState = loginViewModel.state.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    LoginContent(
        loginState = loginState,
        doOnPasswordInput = { password -> loginViewModel.inputPassword(password) },
        doOnLoginButtonClick = {
            coroutineScope.launch {
                loginViewModel.login()
            }
        }
    )
}

@Composable
fun LoginContent(
    loginState: LoginState,
    doOnPasswordInput: (String) -> Unit,
    doOnLoginButtonClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, backgroundColor = Blue700) {
        Column(modifier = Modifier.fillMaxSize()) {
            LoginTitle()
            LoginLayout(
                loginState = loginState,
                doOnPasswordInput = doOnPasswordInput,
                doOnLoginButtonClick = doOnLoginButtonClick
            )
        }
    }
}

@Composable
fun LoginTitle() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_image),
            contentDescription = "login_image"
        )
        val title = "쓰윽싹"
        val comment = "청소 검사를 간편하게"
        Column(horizontalAlignment = Alignment.End) {
            Text(text = title, color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = comment, color = Color.White, fontSize = 17.sp)
        }
    }
}

@Composable
fun LoginLayout(
    loginState: LoginState,
    doOnPasswordInput: (String) -> Unit,
    doOnLoginButtonClick: () -> Unit
) {
    if (loginState.hasLogin) {
        StartCleanLayout(loginState = loginState)
    } else {
        NeedLoginLayout(
            loginState = loginState,
            doOnPasswordInput = doOnPasswordInput,
            doOnLoginButtonClick = doOnLoginButtonClick
        )
    }
}

@Composable
fun StartCleanLayout(loginState: LoginState) {
    LoginColumn {

    }
}

@Composable
fun NeedLoginLayout(
    loginState: LoginState,
    doOnPasswordInput: (String) -> Unit,
    doOnLoginButtonClick: () -> Unit
) {
    LoginColumn {
        val askInputPasswordComment = "비밀번호를 입력홰 주세요"
        Text(text = askInputPasswordComment)
        OutlinedTextField(value = loginState.password, onValueChange = doOnPasswordInput)
        Spacer(modifier = Modifier.height(100.dp))
        LoginButton(loginState = loginState, doOnLoginButtonClick = doOnLoginButtonClick)
    }
}

@Composable
fun LoginButton(loginState: LoginState, doOnLoginButtonClick: () -> Unit) {
    val text = if (loginState.isLoading) "로딩중" else "로그인"
    Button(onClick = doOnLoginButtonClick) {
        Text(text = text)
    }
}

@Composable
fun LoginColumn(contents: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(
                Color.White
            )
    ) {
        contents()
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    LoginContent(
        loginState = LoginState.initial(),
        doOnPasswordInput = {},
        doOnLoginButtonClick = {})
}