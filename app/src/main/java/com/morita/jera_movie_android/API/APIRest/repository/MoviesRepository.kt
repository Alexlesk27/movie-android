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

    suspend fun moviesPopular(): Flow<ResponseState<MovieResponse>> {

        return flow {
            try {
                val response = api.getPopularMovie()
                emit(ResponseState.Success(response))
            } catch (error: Exception) {
                emit(ResponseState.Error(error))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun Upcoming(): Flow<ResponseState<MovieResponse>> {

        return flow {
            try {
                val response = api.getUpcoming()
                emit(ResponseState.Success(response))
            } catch (error: Exception) {
                emit(ResponseState.Error(error))
            }
        }.flowOn(Dispatchers.IO)
    }



    sealed class ResponseState<out T> {
        data class Success<out T>(val value: T) : ResponseState<T>()
        data class Error(val error: Throwable) : ResponseState<Nothing>()
    }

}