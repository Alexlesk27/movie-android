package com.morita.jera_movie_android.Features.Home

import ListState
import android.util.Log
import androidx.lifecycle.*
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class UpcomingMovieViewModel(
    val movieApi: MoviesRepository
) : ViewModel() {

    private var _apiMovieUpcoming = MutableStateFlow<ListState<List<Movie>>>(ListState.New)
    val apiMovieUpcoming: StateFlow<ListState<List<Movie>>> =
        _apiMovieUpcoming.asStateFlow()

    init {
        getUpcoming()
    }

    private fun getUpcoming() {
        viewModelScope.launch {
            movieApi.Upcoming().onStart {
                _apiMovieUpcoming.value = ListState.Loading
            }.collect {
                when (it) {
                    is MoviesRepository.ResponseState.Success -> {
                        _apiMovieUpcoming.value = ListState.Success(it.value!!.Movie)
                    }

                    is MoviesRepository.ResponseState.Error -> {
                        _apiMovieUpcoming.value = ListState.Error(errorId = 1)

                    }
                }
            }
        }

    }
}