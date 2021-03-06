package com.ssag.ssag_admin.feature.clean

import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.parameter.PostCleanStateParameter
import org.threeten.bp.LocalDate

private val secondFloorRooms = 203..222
private val thirdFloorRooms = 325 downTo 303

private val fourthFloorRooms = 422 downTo 401
private val fifthFloorRooms = 501..518

fun CheckCleanState.reduceSetRoomState(roomState: RoomStateEntity): CheckCleanState =
    this.copy(roomState = roomState)

fun CheckCleanState.reduceRoomRange(isManTeacher: Boolean): CheckCleanState {
    val roomList =
        if (isManTeacher) fifthFloorRooms + fourthFloorRooms + thirdFloorRooms + secondFloorRooms
        else secondFloorRooms + thirdFloorRooms + fourthFloorRooms + fifthFloorRooms
    return this.copy(
        roomList = roomList,
        roomIndex = 0
    )
}


fun CheckCleanState.reduceMoveRoom(room: Int): CheckCleanState {
    val roomIndex = roomList.indexOf(room)
    val beforeRoom = if (isNotFirstRoom() && roomIndex > 0) roomList[roomIndex - 1] else 0
    val nextRoom =
        if (isNotLastRoom() && roomIndex < roomList.size - 1) roomList[roomIndex + 1] else 0
    return this.copy(
        beforeRoomNumber = beforeRoom,
        roomNumber = room,
        nextRoomNumber = nextRoom,
        roomIndex = roomIndex
    )
}

fun CheckCleanState.reduceNextRoom(): CheckCleanState {
    val newRoomIndex = roomIndex + 1
    val nextRoom = if (nextRoomIsLastRoom()) 0 else roomList[newRoomIndex + 1]
    return copy(
        roomNumber = roomList[newRoomIndex],
        nextRoomNumber = nextRoom,
        beforeRoomNumber = roomList[newRoomIndex - 1],
        roomIndex = roomIndex + 1
    )
}

fun CheckCleanState.reduceBeforeRoom(): CheckCleanState {
    val newRoomIndex = roomIndex - 1
    val beforeRoom = if (beforeRoomIsFirstRoom()) 0 else roomList[newRoomIndex - 1]
    return this.copy(
        roomNumber = roomList[newRoomIndex],
        beforeRoomNumber = beforeRoom,
        nextRoomNumber = roomList[newRoomIndex + 1],
        roomIndex = roomIndex - 1
    )
}

fun CheckCleanState.isNotFirstRoom(): Boolean =
    roomIndex > 0

fun CheckCleanState.isNotLastRoom(): Boolean =
    roomIndex < roomList.size - 1

private fun CheckCleanState.beforeRoomIsFirstRoom(): Boolean =
    roomIndex <= 1

private fun CheckCleanState.nextRoomIsLastRoom(): Boolean =
    roomIndex >= roomList.size - 2


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