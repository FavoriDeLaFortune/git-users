package com.example.git_users.data.model

sealed interface NetworkState<out T> {
    data class Error(val code: Int, val message: String): NetworkState<Nothing>
    data class Success<T>(val body: T): NetworkState<T>
}