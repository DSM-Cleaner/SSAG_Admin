package com.ssag.ssag_admin.feature.clean

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ssag.domain.clean.entity.CleanStateEntity
import com.ssag.domain.clean.entity.RoomStateEntity
import com.ssag.domain.clean.entity.StudentEntity
import com.ssag.ssag_admin.R
import com.ssag.ssag_admin.ui.theme.Blue900
import com.ssag.ssag_admin.ui.theme.Gray200

@Composable
fun CheckClean(
    navController: NavController,
    checkCleanViewModel: CheckCleanViewModel = hiltViewModel()
) {
    val checkCleanState = checkCleanViewModel.state.collectAsState().value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        checkCleanViewModel.checkDayIsPersonalCheckDay()

    }

    LaunchedEffect(key1 = checkCleanState.roomNumber) {
        checkCleanViewModel.fetchCleanState()
    }

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
        CheckCleanContent(
            checkCleanState = checkCleanState,
            doOnStudentBedIsClean = { studentId ->
                checkCleanViewModel.setStudentBedIsClean(studentId)
            },
            doOnStudentBedIsNotClean = { studentId ->
                checkCleanViewModel.setStudentBedIsNotClean(studentId)
            },
            doOnStudentClotheIsClean = { studentId ->
                checkCleanViewModel.setStudentClotheIsClean(studentId)
            },
            doOnStudentClotheIsNotClean = { studentId ->
                checkCleanViewModel.setStudentClotheIsNotClean(studentId)
            },
            doOnLightValueChanged = { isNotCompleted ->
                if (isNotCompleted) {
                    checkCleanViewModel.setLightIsNotComplete()
                } else {
                    checkCleanViewModel.setLightIsComplete()
                }
            },
            doOnPlugValueChanged = { isNotCompleted ->
                if (isNotCompleted) {
                    checkCleanViewModel.setPlugIsNotComplete()
                } else {
                    checkCleanViewModel.setPlugIsComplete()
                }
            },
            doOnShoesValueChanged = { isNotCompleted ->
                if (isNotCompleted) {
                    checkCleanViewModel.setShoesAreNotComplete()
                } else {
                    checkCleanViewModel.setShoesAreComplete()
                }
            },
            doOnPersonalPlaceNotCompleted = { studentId ->
                checkCleanViewModel.setPersonalPlaceIsNotComplete(studentId)
            },
            doOnPersonalPlaceCompleted = { studentId ->
                checkCleanViewModel.setPersonalPlaceIsComplete(studentId)
            }
        )
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
    checkCleanState: CheckCleanState,
    doOnStudentBedIsNotClean: (Long) -> Unit,
    doOnStudentBedIsClean: (Long) -> Unit,
    doOnStudentClotheIsNotClean: (Long) -> Unit,
    doOnStudentClotheIsClean: (Long) -> Unit,
    doOnLightValueChanged: (Boolean) -> Unit,
    doOnPlugValueChanged: (Boolean) -> Unit,
    doOnShoesValueChanged: (Boolean) -> Unit,
    doOnPersonalPlaceNotCompleted: (Long) -> Unit,
    doOnPersonalPlaceCompleted: (Long) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(10.dp)
    ) {
        Text(
            text = "통과되지 않은 항목을 체크해 주세요.",
            textAlign = TextAlign.End,
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 10.dp, 5.dp)
        )

        CheckCleanStudent(
            checkCleanState = checkCleanState,
            doOnStudentBedIsNotClean = doOnStudentBedIsNotClean,
            doOnStudentBedIsClean = doOnStudentBedIsClean,
            doOnStudentClotheIsNotClean = doOnStudentClotheIsNotClean,
            doOnStudentClotheIsClean = doOnStudentClotheIsClean
        )


        Spacer(modifier = Modifier.height(15.dp))

        CheckCleanRoom(
            roomState = checkCleanState.roomState,
            doOnLightValueChanged = doOnLightValueChanged,
            doOnPlugValueChanged = doOnPlugValueChanged,
            doOnShoesValueChanged = doOnShoesValueChanged
        )

        Spacer(modifier = Modifier.height(15.dp))

        if (checkCleanState.isPersonalCheckDay) {
            CheckCleanPersonalSpace(
                students = checkCleanState.roomState.students,
                doOnPersonalPlaceCompleted = doOnPersonalPlaceCompleted,
                doOnPersonalPlaceNotCompleted = doOnPersonalPlaceNotCompleted
            )
        }
    }
}

@Composable
fun CheckCleanTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(title, fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 18.sp)
    }
}

@Composable
fun CheckCleanContentText(content: String) {
    Text(text = content, color = Blue900, fontWeight = FontWeight.SemiBold)
}

@Composable
fun CheckCleanStudent(
    checkCleanState: CheckCleanState,
    doOnStudentBedIsNotClean: (Long) -> Unit,
    doOnStudentBedIsClean: (Long) -> Unit,
    doOnStudentClotheIsNotClean: (Long) -> Unit,
    doOnStudentClotheIsClean: (Long) -> Unit
) {
    CheckCleanCardView {
        CheckCleanStudentTitle()

        checkCleanState.roomState.students.forEach { student ->
            CheckCleanStudentRow(
                student = student,
                doOnBedToggleClick = { isChecked ->
                    if (isChecked) doOnStudentBedIsNotClean(student.id)
                    else doOnStudentBedIsClean(student.id)
                },
                doOnClotheToggleClick = { isChecked ->
                    if (isChecked) doOnStudentClotheIsNotClean(student.id)
                    else doOnStudentClotheIsClean(student.id)
                }
            )
        }
    }
}

@Composable
fun CheckCleanRoom(
    roomState: RoomStateEntity,
    doOnLightValueChanged: (Boolean) -> Unit,
    doOnPlugValueChanged: (Boolean) -> Unit,
    doOnShoesValueChanged: (Boolean) -> Unit
) {
    CheckCleanCardView {
        CheckCleanTitle("호실공통항목")

        CheckCleanRoomRow(
            title = "호실 내 소등하기",
            isChecked = roomState.lightIsNotComplete,
            doOnValueChange = doOnLightValueChanged
        )

        CheckCleanRoomRow(
            title = "전기 콘센트 뽑기",
            isChecked = roomState.plugIsNotComplete,
            doOnValueChange = doOnPlugValueChanged
        )

        CheckCleanRoomRow(
            title = "바닥 정리 / 신발 정리",
            isChecked = roomState.shoesAreNotComplete,
            doOnValueChange = doOnShoesValueChanged
        )
    }
}

@Composable
fun CheckCleanRoomRow(title: String, isChecked: Boolean, doOnValueChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        CheckCleanContentText(title)

        CleanToggleButton(isChecked = isChecked, onCheckValueChange = doOnValueChange)
    }
}

@Composable
fun CheckCleanStudentTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(62.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CheckCleanContentText("침구정리")
            CheckCleanContentText("개인물품 정리")
        }
    }
}

@Composable
fun CheckCleanCardView(content: @Composable () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
            .border(1.dp, Gray200, RoundedCornerShape(5.dp))
    ) {
        content()
    }
}

@Composable
fun CheckCleanStudentRow(
    student: StudentEntity,
    doOnBedToggleClick: (Boolean) -> Unit,
    doOnClotheToggleClick: (Boolean) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        StudentInfoView(student = student)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            CleanToggleButton(
                isChecked = student.cleanState.beddingIsNotClean,
                onCheckValueChange = doOnBedToggleClick
            )

            CleanToggleButton(
                isChecked = student.cleanState.clotheIsNotClean,
                onCheckValueChange = doOnClotheToggleClick
            )
        }
    }
}

@Composable
fun CheckCleanPersonalSpace(
    students: List<StudentEntity>,
    doOnPersonalPlaceCompleted: (Long) -> Unit,
    doOnPersonalPlaceNotCompleted: (Long) -> Unit
) {
    CheckCleanCardView {
        CheckCleanTitle("개인별 청소구역")

        students.forEach { student ->
            CheckCleanPersonalRow(
                student = student,
                isChecked = student.cleanState.personalPlaceIsNotClean ?: false,
                doOnValueChange = { isChecked ->
                    if (isChecked) {
                        doOnPersonalPlaceNotCompleted(student.id)
                    } else {
                        doOnPersonalPlaceCompleted(student.id)
                    }
                }
            )
        }
    }
}

@Composable
fun CheckCleanPersonalRow(
    student: StudentEntity,
    isChecked: Boolean,
    doOnValueChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        StudentInfoView(student = student)

        CleanToggleButton(isChecked = isChecked, onCheckValueChange = doOnValueChange)
    }
}

@Composable
fun StudentInfoView(student: StudentEntity) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
        val bedPosition = student.bedPosition
        Text(
            text = bedPosition,
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        val gcn = student.gcn.toString()
        Text(text = gcn, fontSize = 12.sp)

        val name = student.name
        Text(text = name, fontSize = 12.sp)
    }
}

@Composable
fun CleanToggleButton(isChecked: Boolean, onCheckValueChange: (Boolean) -> Unit) {
    IconToggleButton(
        checked = isChecked,
        onCheckedChange = onCheckValueChange
    ) {
        val iconResId = if (isChecked) R.drawable.ic_check else R.drawable.ic_un_check
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = "check_toggle"
        )
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
        CheckCleanStudentRow(
            student = StudentEntity(
                0L,
                "A",
                3202,
                "김재원",
                CleanStateEntity(
                    beddingIsNotClean = false,
                    clotheIsNotClean = false,
                    personalPlaceIsNotClean = false
                )
            ),
            {},
            {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckCleanContentPreview() {
    CheckCleanContent(
        checkCleanState = CheckCleanState(
            roomNumber = 501,
            showSelectRoomDialog = false,
            roomState = RoomStateEntity(
                lightIsNotComplete = false,
                plugIsNotComplete = false,
                shoesAreNotComplete = false,
                students = listOf(
                    StudentEntity(
                        12,
                        "A",
                        3202,
                        "김재원",
                        CleanStateEntity(
                            beddingIsNotClean = true,
                            clotheIsNotClean = true,
                            personalPlaceIsNotClean = true
                        )
                    ),
                    StudentEntity(
                        13,
                        "B",
                        3302,
                        "조호원",
                        CleanStateEntity(
                            beddingIsNotClean = true,
                            clotheIsNotClean = true,
                            personalPlaceIsNotClean = true
                        )
                    )
                )
            ),
            isPersonalCheckDay = true,
            isManTeacher = true,
            nextRoomNumber = 0,
            beforeRoomNumber = 0
        ),
        doOnStudentBedIsClean = {},
        doOnStudentBedIsNotClean = {},
        doOnStudentClotheIsClean = {},
        doOnStudentClotheIsNotClean = {},
        doOnShoesValueChanged = {},
        doOnPlugValueChanged = {},
        doOnLightValueChanged = {},
        doOnPersonalPlaceNotCompleted = {},
        doOnPersonalPlaceCompleted = {}
    )
}