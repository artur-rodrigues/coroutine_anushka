package com.anushka.retrodemo1

sealed class Result {
    class Success<T> (val success: T): Result()
    class Error (val exception: String): Result()

    companion object {
        fun <T> sucess(t: T) = Success(t)
        fun error(exception: String) = Error(exception)
    }
}