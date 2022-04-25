package com.ssag.ssag_admin.feature.clean

import com.ssag.ssag_admin.base.Intent

sealed class CheckCleanIntent : Intent {

    object MoveToNextRoom : CheckCleanIntent()
    object MoveToBeforeRoom : CheckCleanIntent()
    data class MoveToRoom(val roomNumber: Int) : CheckCleanIntent()

    object CheckLight : CheckCleanIntent()
    object CheckPlug : CheckCleanIntent()
    object CheckShoes : CheckCleanIntent()
}