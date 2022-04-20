package com.ssag.ssag_admin.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S : State, I : Intent, E: Event> : ViewModel() {

    private val reducer = BaseViewModelReducer()

    abstract val initialState: S
    val state: StateFlow<S> = reducer.state

    private val _event = MutableEventFlow<Event>()
    val event = _event.asEventFlow()

    fun sendIntent(intent: I) {
        reducer.sendIntent(intent)
    }

    fun setState(state: S) {
        reducer.setState(state)
    }

    abstract fun reduceIntent(oldState: S, intent: I)

    inner class BaseViewModelReducer : Reducer<S, I>(initialState) {

        override fun reduce(oldState: S, intent: I) {
            reduceIntent(oldState, intent)
        }
    }
}