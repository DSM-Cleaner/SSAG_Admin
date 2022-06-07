package com.ssag.ssag_admin.feature.clean

sealed class CheckCleanSideEffect {
    object FetchFail : CheckCleanSideEffect()
    object PostFail : CheckCleanSideEffect()
}