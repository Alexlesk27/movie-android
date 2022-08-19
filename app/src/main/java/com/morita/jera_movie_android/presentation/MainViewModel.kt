package com.morita.jera_movie_android.presentation

import ListState
import android.util.Log
import androidx.lifecycle.*
import com.morita.jera_movie_android.data.remote.Model.Movie
import com.morita.jera_movie_android.data.remote.Network.MoviesReponsitory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class MainViewModel(
    val movieApi: MoviesReponsitory
) : ViewModel() {

     private var _apiMoviePopularState = MutableStateFlow<ListState<List<Movie>>>(ListState.New)
     val apiMoviePopularState : StateFlow <ListState<List<Movie>>> =_apiMoviePopularState.asStateFlow()

    private var _apiMovieUpcomingState = MutableStateFlow<ListState<List<Movie>>>(ListState.New)
    val apiMovieUpcomingState : StateFlow <ListState<List<Movie>>> =_apiMovieUpcomingState.asStateFlow()

    init {
        getPopularMovie()
        getUpcoming()
    }

  private  fun getPopularMovie() {
        viewModelScope.launch {
            movieApi.moviesPopular().collect {
                when (it) {
                    is MoviesReponsitory.ResponseState.Success -> {
                        Log.i("lista", "$it")
                        _apiMoviePopularState.value = ListState.Success(it.value!!.Movie)
                    }

                    is MoviesReponsitory.ResponseState.Error -> {

                    }
                }
            }
        }

    }


    private  fun getUpcoming() {
        viewModelScope.launch {
            movieApi.Upcoming().collect {
                when (it) {
                    is MoviesReponsitory.ResponseState.Success -> {
                        Log.i("lista", "$it")
                        _apiMovieUpcomingState.value = ListState.Success(it.value!!.Movie)
                    }

                    is MoviesReponsitory.ResponseState.Error -> {

                    }
                }
            }
        }

    }

}