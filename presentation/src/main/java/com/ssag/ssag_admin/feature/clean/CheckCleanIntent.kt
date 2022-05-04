package com.ssag.ssag_admin.feature.clean

import com.ssag.ssag_admin.base.Intent

sealed class CheckCleanIntent : Intent {

    object MoveToNextRoom : CheckCleanIntent()
    object MoveToBeforeRoom : CheckCleanIntent()
    data class MoveToRoom(val roomNumber: Int) : CheckCleanIntent()

    object SetLightIsClean : CheckCleanIntent()
    object SetLightIsNotClean : CheckCleanIntent()

    object SetPlugIsClean : CheckCleanIntent()
    object SetPlugIsNotClean : CheckCleanIntent()

    object SetShoesAreClean : CheckCleanIntent()
    object SetShoesAreNotClean : CheckCleanIntent()

    data class SetStudentBedIsClean(val studentId: Long) : CheckCleanIntent()
    data class SetStudentBedIsNotClean(val studentId: Long) : CheckCleanIntent()
    data class SetStudentClotheIsClean(val studentId: Long) : CheckCleanIntent()
    data class SetStudentClotheIsNotClean(val studentId: Long) : CheckCleanIntent()
}