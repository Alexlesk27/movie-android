package com.morita.jera_movie_android.Models

class ListReponse {

    sealed class ResponseState<out T> {
        data class Success<out T>(val value: T) : ResponseState<T>()
        data class Error(val error: Throwable) : ResponseState<Nothing>()
    }
}