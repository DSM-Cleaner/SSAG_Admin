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

    }

    sealed class CheckCleanEvent : Event {

    }
}