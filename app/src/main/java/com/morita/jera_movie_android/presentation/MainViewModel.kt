package com.morita.jera_movie_android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.morita.jera_movie_android.data.remote.MovieApi
import com.morita.jera_movie_android.data.remote.dto.PopularMovieDto
import com.morita.jera_movie_android.data.remote.model.Movie
import com.morita.jera_movie_android.data.remote.response.PopularMovieResponse
import com.morita.jera_movie_android.domain.PopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class MainViewModel(
    val api: MovieApi
) : ViewModel() {


    private val _porvir = MutableLiveData<List<PopularMovie>>()
    val povir: LiveData<List<PopularMovie>> get() = _porvir


    private val _popularMovie = MutableLiveData<List<PopularMovie>>()
    val popularMovie: LiveData<List<PopularMovie>> get() = _popularMovie

    private val _popularMovieErrorResponse = MutableLiveData<String>()
    val popularMovieErrorResponse: LiveData<String?> get() = _popularMovieErrorResponse





    suspend fun getpopularMovie() = withContext(Dispatchers.Main) {
        val call: Call<PopularMovieResponse> = api.getPopularMovie()
        call.enqueue(
            object : Callback<PopularMovieResponse> {
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>


                ) {
                    if (response.isSuccessful) {
                        _popularMovie.value =
                            response.body()?.popularMovie?.map { it.toPopularMovie() }
                    }
                }

                override fun onFailure(call: Call<PopularMovieResponse>, error: Throwable) {
                    _popularMovieErrorResponse.value = error.message
                }

            }
        )

    }



    suspend fun getPorVir() = withContext(Dispatchers.Main) {
        val call: Call<PopularMovieResponse> = api.getPorVir()
        call.enqueue(
            object : Callback<PopularMovieResponse> {
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>


                ) {
                    if (response.isSuccessful) {
                        _porvir.value =
                            response.body()?.popularMovie?.map { it.toPopularMovie() }
                    }
                }

                override fun onFailure(call: Call<PopularMovieResponse>, error: Throwable) {
                    _popularMovieErrorResponse.value = error.message
                }

            }
        )

    }



}

