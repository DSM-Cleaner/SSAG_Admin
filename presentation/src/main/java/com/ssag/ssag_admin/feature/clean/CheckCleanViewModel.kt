package com.ssag.ssag_admin.feature.clean

import androidx.lifecycle.ViewModel
import com.ssag.domain.feature.clean.entity.RoomStateEntity
import com.ssag.domain.feature.clean.usecase.FetchRoomStateUseCase
import com.ssag.domain.feature.clean.usecase.PostCleanStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CheckCleanViewModel @Inject constructor(
    private val fetchRoomStateUseCase: FetchRoomStateUseCase,
    private val postCleanStateUseCase: PostCleanStateUseCase
) : ContainerHost<CheckCleanState, CheckCleanSideEffect>, ViewModel() {

    override val container: Container<CheckCleanState, CheckCleanSideEffect> =
        container(CheckCleanState.initial())

    private fun fetchCleanState() = intent {
        kotlin.runCatching {
            fetchRoomStateUseCase.execute(state.roomNumber)
        }.onFailure {
            postSideEffect(CheckCleanSideEffect.FetchFail)
        }.also { roomState ->
            reduce { state.reduceSetRoomState(roomState.getOrDefault(RoomStateEntity.default())) }
        }
    }

    private fun postCleanState(postRoomState: CheckCleanState) = intent {
        kotlin.runCatching {
            val parameter = postRoomState.fetchPostCleanParameter()
            postCleanStateUseCase.execute(parameter)
        }.onSuccess {

        }.onFailure {
            postSideEffect(CheckCleanSideEffect.PostFail)
        }
    }

    fun doneCheckClean() = intent {
        postCleanState(state)
    }

    fun setTeacherGender(isMan: Boolean) = intent {
        reduce { state.reduceIsManTeacher(isMan) }
    }

    fun setRoomRange(isManTeacher: Boolean) = intent {
        reduce { state.reduceRoomRange(isManTeacher) }
    }

    fun setStartRoom() = intent {
        reduce { state.reduceMoveRoom(state.roomList[0]) }
        fetchCleanState()
    }

    fun checkDayIsPersonalCheckDay() = intent {
        reduce { state.reduceDayIsPersonalCheckDay() }
    }

    fun moveToRoom(room: Int) = intent {
        reduce { state.reduceMoveRoom(room) }
    }

    fun doOnSelectRoom() {
        fetchCleanState()
    }

    fun moveToNextRoom() = intent {
        postCleanState(state)
        if (state.isNotLastRoom()) {
            reduce { state.reduceNextRoom() }
            fetchCleanState()
        }
    }

    fun moveToBeforeRoom() = intent {
        postCleanState(state)
        if (state.isNotFirstRoom()) {
            reduce { state.reduceBeforeRoom() }
            fetchCleanState()
        }
    }

    fun showSelectRoomDialog() = intent {
        postCleanState(state)
        reduce { state.reduceShowSelectRoomDialog() }
    }

    fun dismissSelectRoomDialog() = intent {
        reduce { state.reduceDismissSelectRoomDialog() }
        fetchCleanState()
    }

    fun setLightIsComplete() = intent {
        reduce { state.reduceLightIsComplete() }
    }

    fun setLightIsNotComplete() = intent {
        reduce { state.reduceLightIsNotComplete() }
    }

    fun setPlugIsComplete() = intent {
        reduce { state.reducePlugIsComplete() }
    }

    fun setPlugIsNotComplete() = intent {
        reduce { state.reducePlugIsNotComplete() }
    }

    fun setShoesAreComplete() = intent {
        reduce { state.reduceShoesAreComplete() }
    }

    fun setShoesAreNotComplete() = intent {
        reduce { state.reduceShoesAreNotComplete() }
    }

    fun setStudentBedIsClean(id: Long) = intent {
        reduce { state.reduceStudentBedIsClean(id) }
    }

    fun setStudentBedIsNotClean(id: Long) = intent {
        reduce { state.reduceStudentBedIsNotClean(id) }
    }

    fun setStudentClotheIsClean(id: Long) = intent {
        reduce { state.reduceStudentClothIsClean(id) }
    }

    fun setStudentClotheIsNotClean(id: Long) = intent {
        reduce { state.reduceStudentClothIsNotClean(id) }
    }

    fun setPersonalPlaceIsComplete(id: Long) = intent {
        reduce { state.reduceStudentPersonalPlaceIsComplete(id) }
    }

    fun setPersonalPlaceIsNotComplete(id: Long) = intent {
        reduce { state.reduceStudentPersonalPlaceIsNotComplete(id) }
    }
}