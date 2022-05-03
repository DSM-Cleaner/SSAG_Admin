package com.ssag.ssag_admin.feature.clean

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CheckClean(
    navController: NavController,
    checkCleanViewModel: CheckCleanViewModel = hiltViewModel()
) {
    val checkCleanState = checkCleanViewModel.state.collectAsState().value

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${checkCleanState.roomNumber}호",
                        style = TextStyle(
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        modifier = Modifier
                            .clickable {
                                //show dialog
                            }
                            .padding(10.dp)
                    )
                },
                actions = {
                    Text(
                        text = "검사완료",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable {
                                //done
                            }
                            .padding(10.dp)
                    )
                },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White,
                elevation = 12.dp
            )
        }
    ) {
        CheckCleanContent(checkCleanState = checkCleanState)
    }
}

@Composable
fun CheckCleanContent(
    checkCleanState: CheckCleanState
) {

}