package com.morita.jera_movie_android.Features.Home.upcoming.useCases

import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
import com.morita.jera_movie_android.Models.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface UpcomingMoviesUseCaseInterface {
    suspend fun execute(): Flow<MoviesRepository.ResponseState<MovieResponse>>
}

class UpcomingMovieUseCase(

    private val moviesRepository: MoviesRepository
) : UpcomingMoviesUseCaseInterface {
    override suspend fun execute(): Flow<MoviesRepository.ResponseState<MovieResponse>> {
        return flow {
            try {
                val response = moviesRepository.Upcoming()
                emit(MoviesRepository.ResponseState.Success(response))
            } catch (error: Exception) {
                emit(MoviesRepository.ResponseState.Error(error))
            }
        }.flowOn(Dispatchers.IO)

    }
}