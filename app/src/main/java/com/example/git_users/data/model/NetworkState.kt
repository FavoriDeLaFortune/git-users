package com.example.git_users.data.model

sealed interface NetworkState<out T> {
    data object Error: NetworkState<Nothing>
    data class Success<T>(val body: T): NetworkState<T>
}