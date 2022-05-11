package com.ssag.ssag_admin.feature.clean

import com.ssag.ssag_admin.base.BaseViewModel
import com.ssag.ssag_admin.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class CheckCleanViewModel @Inject constructor(

) : BaseViewModel<CheckCleanState, CheckCleanIntent, CheckCleanViewModel.CheckCleanEvent>() {

    override val initialState: CheckCleanState
        get() = CheckCleanState.initial()

    suspend fun fetchCleanState() {
        checkDayIsPersonalCheckDay()
    }

    private fun checkDayIsPersonalCheckDay() {
        if (LocalDate.now().isPersonalCheckDay()) {
            sendIntent(CheckCleanIntent.SetDayIsPersonalCheckDay)
        } else {
            sendIntent(CheckCleanIntent.SetDayIsNotPersonalCheckDay)
        }
    }

    private fun LocalDate.isPersonalCheckDay() =
        this.dayOfWeek.value == 5 || this.dayOfWeek.value == 3

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

    override fun reduceIntent(oldState: CheckCleanState, intent: CheckCleanIntent) {
        when (intent) {
            is CheckCleanIntent.MoveToBeforeRoom -> TODO()
            is CheckCleanIntent.MoveToNextRoom -> TODO()
            is CheckCleanIntent.MoveToRoom -> TODO()

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
        }
    }

    sealed class CheckCleanEvent : Event {

    }
}