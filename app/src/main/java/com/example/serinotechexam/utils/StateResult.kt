package com.example.serinotechexam.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed interface StateResult<out T> {
    data class Success<T>(val data: T): StateResult<T>
    data class Error(val exception: Throwable): StateResult<Nothing>
    data object Loading: StateResult<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<StateResult<T>> {
    return this
        .map<T, StateResult<T>> { StateResult.Success(it) }
        .onStart { emit(StateResult.Loading) }
        .catch { emit(StateResult.Error(it)) }
}