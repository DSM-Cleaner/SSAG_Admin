package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.parameter.PostCleanStateParameter
import org.threeten.bp.LocalDate

fun CheckCleanState.reduceSetRoomState(roomState: RoomStateEntity): CheckCleanState =
    this.copy(roomState = roomState)

fun CheckCleanState.reduceMoveRoom(room: Int): CheckCleanState =
    this.copy(roomNumber = room)

fun CheckCleanState.reduceIsManTeacher(isMan: Boolean) =
    this.copy(isManTeacher = isMan)

fun CheckCleanState.reduceDayIsPersonalCheckDay(): CheckCleanState =
    this.copy(isPersonalCheckDay = LocalDate.now().isPersonalCheckDay())

private fun LocalDate.isPersonalCheckDay() =
    this.isTuesday() || this.isFriday()

private fun LocalDate.isTuesday(): Boolean =
    this.dayOfWeek.value == 2

private fun LocalDate.isFriday(): Boolean =
    this.dayOfWeek.value == 5


fun CheckCleanState.reduceShowSelectRoomDialog(): CheckCleanState =
    this.copy(showSelectRoomDialog = true)

fun CheckCleanState.reduceDismissSelectRoomDialog(): CheckCleanState =
    this.copy(showSelectRoomDialog = false)


fun CheckCleanState.reduceLightIsComplete(): CheckCleanState =
    this.copy(roomState = this.roomState.copy(lightIsNotComplete = false))

fun CheckCleanState.reduceLightIsNotComplete(): CheckCleanState =
    this.copy(roomState = this.roomState.copy(lightIsNotComplete = true))


fun CheckCleanState.reducePlugIsComplete(): CheckCleanState =
    this.copy(roomState = this.roomState.copy(plugIsNotComplete = false))

fun CheckCleanState.reducePlugIsNotComplete(): CheckCleanState =
    this.copy(roomState = this.roomState.copy(plugIsNotComplete = true))


fun CheckCleanState.reduceShoesAreComplete(): CheckCleanState =
    this.copy(roomState = this.roomState.copy(shoesAreNotComplete = false))

fun CheckCleanState.reduceShoesAreNotComplete(): CheckCleanState =
    this.copy(roomState = this.roomState.copy(shoesAreNotComplete = true))


fun CheckCleanState.reduceStudentBedIsClean(id: Long): CheckCleanState =
    this.copy(
        roomState = this.roomState.copy(
            students = this.roomState.students.map { oldStateStudent ->
                if (oldStateStudent.id == id) oldStateStudent.copy(
                    cleanState = oldStateStudent.cleanState.copy(beddingIsNotClean = false)
                )
                else oldStateStudent
            }
        )
    )

fun CheckCleanState.reduceStudentBedIsNotClean(id: Long): CheckCleanState =
    this.copy(
        roomState = this.roomState.copy(
            students = this.roomState.students.map { oldStateStudent ->
                if (oldStateStudent.id == id) oldStateStudent.copy(
                    cleanState = oldStateStudent.cleanState.copy(beddingIsNotClean = true)
                )
                else oldStateStudent
            }
        )
    )


fun CheckCleanState.reduceStudentClothIsClean(id: Long): CheckCleanState =
    this.copy(
        roomState = this.roomState.copy(
            students = this.roomState.students.map { oldStateStudent ->
                if (oldStateStudent.id == id) oldStateStudent.copy(
                    cleanState = oldStateStudent.cleanState.copy(clotheIsNotClean = false)
                )
                else oldStateStudent
            }
        )
    )

fun CheckCleanState.reduceStudentClothIsNotClean(id: Long): CheckCleanState =
    this.copy(
        roomState = this.roomState.copy(
            students = this.roomState.students.map { oldStateStudent ->
                if (oldStateStudent.id == id) oldStateStudent.copy(
                    cleanState = oldStateStudent.cleanState.copy(clotheIsNotClean = true)
                )
                else oldStateStudent
            }
        )
    )


fun CheckCleanState.reduceStudentPersonalPlaceIsComplete(id: Long): CheckCleanState =
    this.copy(
        roomState = this.roomState.copy(
            students = this.roomState.students.map { oldStateStudent ->
                if (oldStateStudent.id == id) oldStateStudent.copy(
                    cleanState = oldStateStudent.cleanState.copy(
                        personalPlaceIsNotClean = false
                    )
                )
                else oldStateStudent
            }
        )
    )

fun CheckCleanState.reduceStudentPersonalPlaceIsNotComplete(id: Long): CheckCleanState =
    this.copy(
        roomState = this.roomState.copy(
            students = this.roomState.students.map { oldStateStudent ->
                if (oldStateStudent.id == id) oldStateStudent.copy(
                    cleanState = oldStateStudent.cleanState.copy(
                        personalPlaceIsNotClean = true
                    )
                )
                else oldStateStudent
            }
        )
    )

fun CheckCleanState.fetchPostCleanParameter(): PostCleanStateParameter =
    PostCleanStateParameter(
        roomId = roomNumber,
        lightIsNotComplete = roomState.lightIsNotComplete,
        plugIsNotComplete = roomState.plugIsNotComplete,
        shoesAreNotComplete = roomState.shoesAreNotComplete,
        studentList = roomState.students
    )