package com.morita.jera_movie_android.API.APIRest.repository

import com.morita.jera_movie_android.API.APIRest.MovieApi
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.Models.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MoviesRepository(
    val api: MovieApi

) {

    suspend fun moviesPopular(): MovieResponse {

        return api.getPopularMovie()
    }

    suspend fun Upcoming(): MovieResponse {

        return api.getUpcoming()

    }

    suspend fun Details(id: Int): Movie {

        return api.getDetails(id)
    }

    sealed class ResponseState<out T> {
        data class Success<out T>(val value: T) : ResponseState<T>()
        data class Error(val error: Throwable) : ResponseState<Nothing>()
    }

}