package com.ssag.ssag_admin.feature.clean

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ssag.domain.clean.entity.StudentEntity
import com.ssag.ssag_admin.ui.theme.Gray200

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
            CheckCleanTopBarContent(
                checkCleanState = checkCleanState,
                doOnSelectRoomClick = { },
                doOnCompleteClick = { navController.popBackStack() }
            )
        }
    ) {
        CheckCleanContent(checkCleanState = checkCleanState)
    }
}

@Composable
fun CheckCleanTopBarContent(
    checkCleanState: CheckCleanState,
    doOnSelectRoomClick: () -> Unit,
    doOnCompleteClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "${checkCleanState.roomNumber}호",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .clickable {
                        doOnSelectRoomClick()
                    }
                    .padding(10.dp)
            )
        },
        actions = {
            Text(
                text = "호실선택",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clickable { doOnSelectRoomClick() }
                    .padding(10.dp)
            )
            Text(
                text = "검사완료",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clickable { doOnCompleteClick() }
                    .padding(10.dp)
            )
        },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        contentColor = Color.White,
        elevation = 12.dp
    )
}

@Composable
fun CheckCleanContent(
    checkCleanState: CheckCleanState
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(12.dp)
    ) {
        Text(
            text = "통과되지 않은 항목을 체크해 주세요.",
            textAlign = TextAlign.End,
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun CheckCleanCardView(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp, 0.dp)
            .border(1.dp, Gray200, RoundedCornerShape(5.dp))
    ) {
        content()
    }
}

@Composable
fun CheckCleanPersonalRow(student: StudentEntity) {
    Row(modifier = Modifier.fillMaxWidth()) {
        PersonalInfoView(student = student)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {

        }
    }
}

@Composable
fun PersonalInfoView(student: StudentEntity) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val bedPosition = student.bedPosition
        val gcn = student.gcn.toString()
        val name = student.name
        Text(
            text = bedPosition,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = gcn)
        Text(text = name)
    }
}

@Preview
@Composable
fun CheckCleanTopBarPreview() {
    CheckCleanTopBarContent(
        checkCleanState = CheckCleanState.initial(),
        doOnSelectRoomClick = { },
        doOnCompleteClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun CheckCleanCardViewPreview() {
    CheckCleanCardView {
        Text(text = "test")
        Text(text = "cardView")
    }
}

@Preview(showBackground = true)
@Composable
fun CheckCleanContentPreview() {
    CheckCleanContent(checkCleanState = CheckCleanState.initial())
}