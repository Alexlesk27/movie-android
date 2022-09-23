package com.morita.jera_movie_android.Features.Home.search

import ListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
import com.morita.jera_movie_android.Features.Home.search.useCases.SearchMoviesUseCaseInterface
import com.morita.jera_movie_android.Models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    val searchMoviesUseCase: SearchMoviesUseCaseInterface
): ViewModel(


) {
    private var _apiSearchMovie = MutableStateFlow<ListState<List<Movie>>>(ListState.New)
    val apiSearchMovie: StateFlow<ListState<List<Movie>>> =
        _apiSearchMovie.asStateFlow()


     fun getSearchMovie(query: String) {
        viewModelScope.launch {
            searchMoviesUseCase.execute(query).onStart {
                _apiSearchMovie.value = ListState.Loading
            }.collect {
                when (it) {
                    is MoviesRepository.ResponseState.Success -> {
                        _apiSearchMovie.value = ListState.Success(it.value!!.Movie)
                    }

                    is MoviesRepository.ResponseState.Error -> {
                        _apiSearchMovie.value = ListState.Error(errorId = 1)

                    }
                }
            }
        }

    }

}