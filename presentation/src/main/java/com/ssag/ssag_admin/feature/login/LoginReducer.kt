package com.ssag.ssag_admin.feature.login

import com.ssag.domain.feature.auth.entity.TeacherEntity

fun LoginState.reduceSetTeacher(teacher: TeacherEntity) =
    this.copy(
        hasLogin = true,
        teacherName = teacher.name,
        startFloor = if (teacher.isManTeacher) 5 else 2,
        isManTeacher = teacher.isManTeacher
    )

fun LoginState.reduceStartLoading() =
    this.copy(isLoading = true)

fun LoginState.reduceFinishLoading() =
    this.copy(isLoading = false)

fun LoginState.reduceInputName(name: String) =
    this.copy(name = name)

fun LoginState.reduceInputPassword(password: String) =
    this.copy(password = password)

fun LoginState.reduceLogout() =
    LoginState.initial()