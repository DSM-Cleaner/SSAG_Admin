package com.ssag.ssag_admin.feature.clean

import com.ssag.ssag_admin.base.Intent

sealed class CheckCleanIntent : Intent {

    object MoveToNextRoom : CheckCleanIntent()
    object MoveToBeforeRoom : CheckCleanIntent()
    data class MoveToRoom(val roomNumber: Int) : CheckCleanIntent()

    object SetDayIsPersonalCheckDay : CheckCleanIntent()
    object SetDayIsNotPersonalCheckDay : CheckCleanIntent()

    object SetTeacherIsMan : CheckCleanIntent()
    object SetTeacherIsWoman : CheckCleanIntent()

    object SetLightIsComplete : CheckCleanIntent()
    object SetLightIsNotComplete : CheckCleanIntent()

    object SetPlugIsComplete : CheckCleanIntent()
    object SetPlugIsNotComplete : CheckCleanIntent()

    object SetShoesAreComplete : CheckCleanIntent()
    object SetShoesAreNotComplete : CheckCleanIntent()

    data class SetStudentBedIsClean(val studentId: Long) : CheckCleanIntent()
    data class SetStudentBedIsNotClean(val studentId: Long) : CheckCleanIntent()
    data class SetStudentClotheIsClean(val studentId: Long) : CheckCleanIntent()
    data class SetStudentClotheIsNotClean(val studentId: Long) : CheckCleanIntent()

    data class SetPersonalPlaceIsNotComplete(val studentId: Long) : CheckCleanIntent()
    data class SetPersonalPlaceIsComplete(val studentId: Long) : CheckCleanIntent()
}