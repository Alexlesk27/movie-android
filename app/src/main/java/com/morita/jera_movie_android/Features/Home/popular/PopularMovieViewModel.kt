package com.morita.jera_movie_android.Features.Home.popular

import ListState
import android.util.Log
import androidx.lifecycle.*
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class PopularMovieViewModel(
    val movieApi: MoviesRepository
) : ViewModel() {

    private var _apiPopularMovie = MutableStateFlow<ListState<List<Movie>>>(ListState.New)
    val apiPopularMovie: StateFlow<ListState<List<Movie>>> =
        _apiPopularMovie.asStateFlow()

    init {
        getPopularMovie()
    }

    private fun getPopularMovie() {
        viewModelScope.launch {
            movieApi.moviesPopular().onStart {
                _apiPopularMovie.value = ListState.Loading
            }.collect {
                when (it) {
                    is MoviesRepository.ResponseState.Success -> {
                        _apiPopularMovie.value = ListState.Success(it.value!!.Movie)
                    }

                    is MoviesRepository.ResponseState.Error -> {
                        _apiPopularMovie.value = ListState.Error(errorId = 1)

                    }
                }
            }
        }

    }

}