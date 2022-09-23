//package com.morita.jera_movie_android.Features.Home.search.useCases
//
//import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
//import com.morita.jera_movie_android.Models.MovieResponse
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//
//interface SearchMoviesUseCaseInterface {
//    suspend fun execute(query: String): Flow<MoviesRepository.ResponseState<MovieResponse>>
//}
//
//class SearchMovieUseCase(
//    private val moviesRepository: MoviesRepository
//) : SearchMoviesUseCaseInterface {
//    override suspend fun execute(query: String): Flow<MoviesRepository.ResponseState<MovieResponse>> {
//        return flow {
//            try {
//                val response = moviesRepository.(query)
//                emit(MoviesRepository.ResponseState.Success(response))
//            } catch (error: Exception) {
//                emit(MoviesRepository.ResponseState.Error(error))
//            }
//        }.flowOn(Dispatchers.IO)
//
//    }
//}