package com.ssag.domain.base

abstract class UseCase<T, E> {
    abstract suspend fun execute(data: T): E
}