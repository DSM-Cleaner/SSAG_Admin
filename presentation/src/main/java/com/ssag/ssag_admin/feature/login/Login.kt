package com.ssag.ssag_admin.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

@Composable
fun Login(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    val loginState = loginViewModel.state.collectAsState().value
    LoginContent(loginState = loginState)
}

@Composable
fun LoginContent(loginState: LoginState) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, backgroundColor = Blue700) {
        Column(modifier = Modifier.fillMaxSize()) {
            LoginTitle()
            LoginLayout(loginState = loginState)
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
fun LoginLayout(loginState: LoginState) {
    if (loginState.hasLogin) {
        StartCleanLayout(loginState = loginState)
    } else {
        NeedLoginLayout(loginState = loginState)
    }
}

@Composable
fun StartCleanLayout(loginState: LoginState) {
    LoginColumn {

    }
}

@Composable
fun NeedLoginLayout(loginState: LoginState) {
    LoginColumn {

    }
}

@Composable
fun LoginColumn(contents: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(
                Color.White
            )
    ) {

    }
}

@Preview
@Composable
fun LoginContentPreview() {
    LoginContent(loginState = LoginState.initial())
}