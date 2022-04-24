package com.ssag.ssag_admin.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<S : State, I : Intent>(initialState: S) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> get() = _state

    fun sendIntent(intent: I) {
        reduce(state.value, intent)
    }

    fun setState(newState: S) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: S, intent: I)
}