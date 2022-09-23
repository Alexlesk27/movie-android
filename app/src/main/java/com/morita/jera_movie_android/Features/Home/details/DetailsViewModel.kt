package com.morita.jera_movie_android.Features.Home.details

import ListState
import android.util.Log
import androidx.lifecycle.*
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DetailsViewModel(
    val movieApi: MoviesRepository
) : ViewModel() {

    private var _apiDetailsMovie = MutableStateFlow<ListState<Movie>>(ListState.New)
    val apiDetailMovie: StateFlow<ListState<Movie>> =
        _apiDetailsMovie.asStateFlow()

     fun getDetailsMovie(id: Int) {
        viewModelScope.launch {
            movieApi.Details(id).onStart {
                _apiDetailsMovie.value = ListState.Loading
            }.collect {
                when (it) {
                    is MoviesRepository.ResponseState.Success -> {
                        _apiDetailsMovie.value = ListState.Success(it.value)
                    }
                    is MoviesRepository.ResponseState.Error -> {
                        _apiDetailsMovie.value = ListState.Error(errorId = 1)

                    }
                }
            }
        }

    }

}