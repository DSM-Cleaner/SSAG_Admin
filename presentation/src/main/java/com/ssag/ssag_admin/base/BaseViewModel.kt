package com.ssag.ssag_admin.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : State, I : Intent, E: Event> : ViewModel() {

    private val reducer = BaseViewModelReducer()

    abstract val initialState: S
    val state: StateFlow<S> = reducer.state

    private val _event = MutableEventFlow<Event>()
    val event = _event.asEventFlow()

    fun sendIntent(intent: I) {
        reducer.sendIntent(intent)
    }

    fun sendEvent(event: E) {
        viewModelScope.launch {
            _event.emit(event)
        }
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