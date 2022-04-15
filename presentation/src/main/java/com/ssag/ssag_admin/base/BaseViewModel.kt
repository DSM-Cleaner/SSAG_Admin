package com.ssag.ssag_admin.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<S : State, I : Intent> : ViewModel() {

    private val reducer = BaseViewModelReducer()

    abstract val initialState: S
    val state: StateFlow<S> = reducer.state

    fun sendIntent(intent: I) {
        reducer.sendIntent(intent)
    }

    fun setState(state: S) {
        reducer.setState(state)
    }

    abstract fun reduceIntent(oldState: S, event: I)

    inner class BaseViewModelReducer : Reducer<S, I>(initialState) {

        override fun reduce(oldState: S, intent: I) {
            reduceIntent(oldState, intent)
        }
    }
}