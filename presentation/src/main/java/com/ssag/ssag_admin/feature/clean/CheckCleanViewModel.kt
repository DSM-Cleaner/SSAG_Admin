package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.clean.parameter.PostCleanStateParameter
import com.ssag.domain.clean.usecase.FetchRoomStateUseCase
import com.ssag.domain.clean.usecase.PostCleanStateUseCase
import com.ssag.ssag_admin.base.BaseViewModel
import com.ssag.ssag_admin.base.Event
import com.ssag.ssag_admin.base.MutableEventFlow
import com.ssag.ssag_admin.base.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CheckCleanViewModel @Inject constructor(
    private val fetchRoomStateUseCase: FetchRoomStateUseCase,
    private val postCleanStateUseCase: PostCleanStateUseCase
) : BaseViewModel<CheckCleanState, CheckCleanIntent, CheckCleanViewModel.CheckCleanEvent>() {

    override val initialState: CheckCleanState
        get() = CheckCleanState.initial()

    private val _failedEvent = MutableEventFlow<CheckCleanFailEvent>()
    val failedEvent = _failedEvent.asEventFlow()

    private val secondFloorRooms = 201..223
    private val thirdFloorRooms = 323 downTo 301

    private val fourthFloorRooms = 423 downTo 401
    private val fifthFloorRooms = 501..523

    private var roomIndex = 0

    private val rooms =
        if (state.value.isManTeacher) fifthFloorRooms + fourthFloorRooms + thirdFloorRooms + secondFloorRooms
        else secondFloorRooms + thirdFloorRooms + fourthFloorRooms + fifthFloorRooms

    suspend fun fetchCleanState() {
        kotlin.runCatching {
            fetchRoomStateUseCase.execute(state.value.roomNumber)
        }.onSuccess { roomState ->
            sendIntent(CheckCleanIntent.SetRoomState(roomState))
        }.onFailure {
            _failedEvent.emit(CheckCleanFailEvent.FetchFail)
        }
    }

    suspend fun postCleanState() {
        kotlin.runCatching {
            val state = state.value
            val roomState = state.roomState
            val parameter = PostCleanStateParameter(
                roomId = state.roomNumber,
                lightIsNotComplete = roomState.lightIsNotComplete,
                plugIsNotComplete = roomState.plugIsNotComplete,
                shoesAreNotComplete = roomState.shoesAreNotComplete,
                studentList = roomState.students
            )
            postCleanStateUseCase.execute(parameter)
        }.onSuccess {

        }.onFailure {
            _failedEvent.emit(CheckCleanFailEvent.PostFail)
        }
    }

    fun setTeacherGender(isMan: Boolean) {
        if (isMan) {
            sendIntent(CheckCleanIntent.SetTeacherIsMan)
        } else {
            sendIntent(CheckCleanIntent.SetTeacherIsWoman)
        }
    }

    fun fetchRooms(): List<Int> =
        rooms

    fun isNotFirstRoom(): Boolean =
        roomIndex > 0

    fun isNotLastRoom(): Boolean =
        roomIndex < rooms.size - 1

    private fun isFirstRoom(): Boolean =
        roomIndex == 0

    private fun isLastRoom(): Boolean =
        roomIndex == rooms.size - 1

    fun setStartRoom() {
        sendIntent(CheckCleanIntent.MoveToRoom(rooms[roomIndex]))
        sendEvent(CheckCleanEvent.DoneSetRoom)
    }

    fun checkDayIsPersonalCheckDay() {
        if (LocalDate.now().isPersonalCheckDay()) {
            sendIntent(CheckCleanIntent.SetDayIsPersonalCheckDay)
        } else {
            sendIntent(CheckCleanIntent.SetDayIsNotPersonalCheckDay)
        }
    }

    private fun LocalDate.isPersonalCheckDay() =
        this.dayOfWeek.value == 5 || this.dayOfWeek.value == 3

    fun moveToRoom(room: Int) {
        sendIntent(CheckCleanIntent.MoveToRoom(room))
    }

    fun moveToNextRoom() {
        sendIntent(CheckCleanIntent.MoveToNextRoom)
        sendEvent(CheckCleanEvent.DoneSetRoom)
    }

    fun moveToBeforeRoom() {
        sendIntent(CheckCleanIntent.MoveToBeforeRoom)
        sendEvent(CheckCleanEvent.DoneSetRoom)
    }

    fun showSelectRoomDialog() {
        sendIntent(CheckCleanIntent.ShowSelectRoomDialog)
    }

    fun dismissSelectRoomDialog() {
        sendIntent(CheckCleanIntent.DismissSelectRoomDialog)
        sendEvent(CheckCleanEvent.DoneSetRoom)
    }

    fun setLightIsComplete() {
        sendIntent(CheckCleanIntent.SetLightIsComplete)
    }

    fun setLightIsNotComplete() {
        sendIntent(CheckCleanIntent.SetLightIsNotComplete)
    }

    fun setPlugIsComplete() {
        sendIntent(CheckCleanIntent.SetPlugIsComplete)
    }

    fun setPlugIsNotComplete() {
        sendIntent(CheckCleanIntent.SetPlugIsNotComplete)
    }

    fun setShoesAreComplete() {
        sendIntent(CheckCleanIntent.SetShoesAreComplete)
    }

    fun setShoesAreNotComplete() {
        sendIntent(CheckCleanIntent.SetShoesAreNotComplete)
    }

    fun setStudentBedIsClean(id: Long) {
        sendIntent(CheckCleanIntent.SetStudentBedIsClean(id))
    }

    fun setStudentBedIsNotClean(id: Long) {
        sendIntent(CheckCleanIntent.SetStudentBedIsNotClean(id))
    }

    fun setStudentClotheIsClean(id: Long) {
        sendIntent(CheckCleanIntent.SetStudentClotheIsClean(id))
    }

    fun setStudentClotheIsNotClean(id: Long) {
        sendIntent(CheckCleanIntent.SetStudentClotheIsNotClean(id))
    }

    fun setPersonalPlaceIsComplete(id: Long) {
        sendIntent(CheckCleanIntent.SetPersonalPlaceIsComplete(id))
    }

    fun setPersonalPlaceIsNotComplete(id: Long) {
        sendIntent(CheckCleanIntent.SetPersonalPlaceIsNotComplete(id))
    }

    override fun reduceIntent(oldState: CheckCleanState, intent: CheckCleanIntent) {
        when (intent) {
            is CheckCleanIntent.SetRoomState -> {
                setState(
                    oldState.copy(
                        roomState = intent.roomState
                    )
                )
            }

            is CheckCleanIntent.MoveToBeforeRoom -> {
                sendEvent(CheckCleanEvent.PostRoomState)
                if (isNotFirstRoom()) {
                    roomIndex -= 1
                    val beforeRoom = if (isFirstRoom()) 0 else rooms[roomIndex - 1]
                    setState(
                        oldState.copy(
                            roomNumber = rooms[roomIndex],
                            beforeRoomNumber = beforeRoom,
                            nextRoomNumber = rooms[roomIndex + 1]
                        )
                    )
                }
            }
            is CheckCleanIntent.MoveToNextRoom -> {
                sendEvent(CheckCleanEvent.PostRoomState)
                if (isNotLastRoom()) {
                    roomIndex += 1
                    val nextRoom = if (isLastRoom()) 0 else rooms[roomIndex + 1]
                    setState(
                        oldState.copy(
                            roomNumber = rooms[roomIndex],
                            nextRoomNumber = nextRoom,
                            beforeRoomNumber = rooms[roomIndex - 1]
                        )
                    )
                }
            }
            is CheckCleanIntent.MoveToRoom -> {
                sendEvent(CheckCleanEvent.PostRoomState)
                roomIndex = rooms.indexOf(intent.roomNumber)
                val beforeRoom = if (isNotFirstRoom()) rooms[roomIndex - 1] else 0
                val nextRoom = if (isNotLastRoom()) rooms[roomIndex + 1] else 0
                setState(
                    oldState.copy(
                        beforeRoomNumber = beforeRoom,
                        roomNumber = intent.roomNumber,
                        nextRoomNumber = nextRoom
                    )
                )
            }

            is CheckCleanIntent.SetLightIsComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(lightIsNotComplete = false)
                    )
                )
            }
            is CheckCleanIntent.SetLightIsNotComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(lightIsNotComplete = true)
                    )
                )
            }

            is CheckCleanIntent.SetPlugIsComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(plugIsNotComplete = false)
                    )
                )
            }
            is CheckCleanIntent.SetPlugIsNotComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(plugIsNotComplete = true)
                    )
                )
            }

            is CheckCleanIntent.SetShoesAreComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(shoesAreNotComplete = false)
                    )
                )
            }
            is CheckCleanIntent.SetShoesAreNotComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(shoesAreNotComplete = true)
                    )
                )
            }

            is CheckCleanIntent.SetStudentBedIsClean -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(
                            students = oldState.roomState.students.map { oldStateStudent ->
                                if (oldStateStudent.id == intent.studentId) oldStateStudent.copy(
                                    cleanState = oldStateStudent.cleanState.copy(beddingIsNotClean = false)
                                )
                                else oldStateStudent
                            }
                        )
                    )
                )
            }
            is CheckCleanIntent.SetStudentBedIsNotClean -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(
                            students = oldState.roomState.students.map { oldStateStudent ->
                                if (oldStateStudent.id == intent.studentId) oldStateStudent.copy(
                                    cleanState = oldStateStudent.cleanState.copy(beddingIsNotClean = true)
                                )
                                else oldStateStudent
                            }
                        )
                    )
                )
            }
            is CheckCleanIntent.SetStudentClotheIsClean -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(
                            students = oldState.roomState.students.map { oldStateStudent ->
                                if (oldStateStudent.id == intent.studentId) oldStateStudent.copy(
                                    cleanState = oldStateStudent.cleanState.copy(clotheIsNotClean = false)
                                )
                                else oldStateStudent
                            }
                        )
                    )
                )
            }
            is CheckCleanIntent.SetStudentClotheIsNotClean -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(
                            students = oldState.roomState.students.map { oldStateStudent ->
                                if (oldStateStudent.id == intent.studentId) oldStateStudent.copy(
                                    cleanState = oldStateStudent.cleanState.copy(clotheIsNotClean = true)
                                )
                                else oldStateStudent
                            }
                        )
                    )
                )
            }

            is CheckCleanIntent.SetDayIsNotPersonalCheckDay -> {
                setState(
                    oldState.copy(
                        isPersonalCheckDay = false
                    )
                )
            }
            is CheckCleanIntent.SetDayIsPersonalCheckDay -> {
                setState(
                    oldState.copy(
                        isPersonalCheckDay = true
                    )
                )
            }

            is CheckCleanIntent.SetPersonalPlaceIsComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(
                            students = oldState.roomState.students.map { oldStateStudent ->
                                if (oldStateStudent.id == intent.studentId) oldStateStudent.copy(
                                    cleanState = oldStateStudent.cleanState.copy(
                                        personalPlaceIsNotClean = false
                                    )
                                )
                                else oldStateStudent
                            }
                        )
                    )
                )
            }
            is CheckCleanIntent.SetPersonalPlaceIsNotComplete -> {
                setState(
                    oldState.copy(
                        roomState = oldState.roomState.copy(
                            students = oldState.roomState.students.map { oldStateStudent ->
                                if (oldStateStudent.id == intent.studentId) oldStateStudent.copy(
                                    cleanState = oldStateStudent.cleanState.copy(
                                        personalPlaceIsNotClean = true
                                    )
                                )
                                else oldStateStudent
                            }
                        )
                    )
                )
            }

            is CheckCleanIntent.SetTeacherIsMan -> {
                setState(
                    oldState.copy(
                        isManTeacher = true
                    )
                )
            }
            is CheckCleanIntent.SetTeacherIsWoman -> {
                setState(
                    oldState.copy(
                        isManTeacher = false
                    )
                )
            }
            is CheckCleanIntent.DismissSelectRoomDialog -> {
                setState(
                    oldState.copy(
                        showSelectRoomDialog = false
                    )
                )
            }
            is CheckCleanIntent.ShowSelectRoomDialog -> {
                setState(
                    oldState.copy(
                        showSelectRoomDialog = true
                    )
                )
            }
        }
    }

    sealed class CheckCleanEvent : Event {
        object DoneSetRoom : CheckCleanEvent()
        object PostRoomState : CheckCleanEvent()
    }

    sealed class CheckCleanFailEvent : Event {
        object FetchFail : CheckCleanFailEvent()
        object PostFail : CheckCleanFailEvent()
    }
}