package com.ssag.ssag_admin.feature.clean

import com.ssag.ssag_admin.base.BaseViewModel
import com.ssag.ssag_admin.base.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckCleanViewModel @Inject constructor(

) : BaseViewModel<CheckCleanState, CheckCleanIntent, CheckCleanViewModel.CheckCleanEvent>() {

    override val initialState: CheckCleanState
        get() = CheckCleanState.initial()

    override fun reduceIntent(oldState: CheckCleanState, intent: CheckCleanIntent) {
        when (intent) {
            is CheckCleanIntent.MoveToBeforeRoom -> TODO()
            is CheckCleanIntent.MoveToNextRoom -> TODO()
            is CheckCleanIntent.MoveToRoom -> TODO()

            is CheckCleanIntent.SetLightIsClean -> {
                setState(
                    oldState.copy(
                        cleanState = oldState.cleanState.copy(
                            lightIsNotComplete = false
                        )
                    )
                )
            }
            is CheckCleanIntent.SetLightIsNotClean -> {
                setState(
                    oldState.copy(
                        cleanState = oldState.cleanState.copy(
                            lightIsNotComplete = true
                        )
                    )
                )
            }

            is CheckCleanIntent.SetPlugIsClean -> {
                setState(
                    oldState.copy(
                        cleanState = oldState.cleanState.copy(
                            plugIsNotComplete = false
                        )
                    )
                )
            }
            is CheckCleanIntent.SetPlugIsNotClean -> {
                setState(
                    oldState.copy(
                        cleanState = oldState.cleanState.copy(
                            plugIsNotComplete = true
                        )
                    )
                )
            }

            is CheckCleanIntent.SetShoesAreClean -> {
                setState(
                    oldState.copy(
                        cleanState = oldState.cleanState.copy(
                            shoesAreNotComplete = false
                        )
                    )
                )
            }
            is CheckCleanIntent.SetShoesAreNotClean -> {
                setState(
                    oldState.copy(
                        cleanState = oldState.cleanState.copy(
                            shoesAreNotComplete = true
                        )
                    )
                )
            }

            is CheckCleanIntent.SetStudentBedIsClean -> {
            }
            is CheckCleanIntent.SetStudentBedIsNotClean -> TODO()
            is CheckCleanIntent.SetStudentClotheIsClean -> TODO()
            is CheckCleanIntent.SetStudentClotheIsNotClean -> TODO()

        }
    }

    sealed class CheckCleanEvent : Event {

    }
}