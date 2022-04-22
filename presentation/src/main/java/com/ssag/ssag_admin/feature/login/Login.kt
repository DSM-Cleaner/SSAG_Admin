package com.ssag.ssag_admin.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ssag.ssag_admin.R
import com.ssag.ssag_admin.base.observeWithLifecycle
import com.ssag.ssag_admin.ui.theme.Blue700
import com.ssag.ssag_admin.ui.theme.Purple700
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(InternalCoroutinesApi::class)
@Composable
fun Login(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val loginState = loginViewModel.state.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        LoginContent(
            loginState = loginState,
            doOnPasswordInput = { password -> loginViewModel.inputPassword(password) },
            doOnLoginButtonClick = {
                coroutineScope.launch {
                    loginViewModel.login()
                }
            }
        )

        loginViewModel.event.observeWithLifecycle {
            when (it) {
                is LoginViewModel.LoginEvent.FailedLogin -> {
                    scaffoldState.snackbarHostState.showSnackbar("로그인을 실패했습니다.")
                }
            }
        }
    }
}

@Composable
fun LoginContent(
    loginState: LoginState,
    doOnPasswordInput: (String) -> Unit,
    doOnLoginButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue700)
    ) {
        LoginTitle()
        LoginLayout(
            loginState = loginState,
            doOnPasswordInput = doOnPasswordInput,
            doOnLoginButtonClick = doOnLoginButtonClick
        )
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
        Text(text = floorText(loginState.startFloor), modifier = Modifier.padding(40.dp))
    }
}

private fun floorText(startFloor: Int): String {
    return if (startFloor <= 3) {
        "2층 / 3층 검사"
    } else {
        "4층 / 5층 검사"
    }
}

@Composable
fun NeedLoginLayout(
    loginState: LoginState,
    doOnPasswordInput: (String) -> Unit,
    doOnLoginButtonClick: () -> Unit
) {
    val authComment = "Tip: 비밀번호를 통해 청소 검사를 하시는 선생님이 누구인지 구별합니다."
    LoginColumn {
        Text(text = authComment, modifier = Modifier.padding(40.dp), color = Blue700)
        PasswordTextField(
            passwordText = loginState.password,
            doOnPasswordInput = doOnPasswordInput,
            doOnInputDone = doOnLoginButtonClick
        )
        Spacer(modifier = Modifier.height(280.dp))
        LoginButton(loginState = loginState, doOnLoginButtonClick = doOnLoginButtonClick)
    }
}

@Composable
fun PasswordTextField(
    passwordText: String,
    doOnPasswordInput: (String) -> Unit,
    doOnInputDone: () -> Unit
) {
    val askInputPasswordComment = "비밀번호를 입력해 주세요"

    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = passwordText,
        onValueChange = doOnPasswordInput,
        colors = textFieldColors(
            textColor = Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                doOnInputDone()
                focusManager.clearFocus()
            }
        ),
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = askInputPasswordComment) }
    )
}

@Composable
fun LoginButton(loginState: LoginState, doOnLoginButtonClick: () -> Unit) {
    val text = if (loginState.isLoading) "로딩중" else "로그인"
    val color = if (loginState.isLoading) Color.Gray else Purple700
    Button(
        onClick = doOnLoginButtonClick, colors = buttonColors(
            backgroundColor = color,
            contentColor = Color.White
        ),
        modifier = Modifier
            .size(250.dp, 40.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Text(text = text)
    }
}

@Composable
fun LoginColumn(contents: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
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

@Preview
@Composable
fun StartCleanLayoutPreview() {
    StartCleanLayout(loginState = LoginState.initial())
}