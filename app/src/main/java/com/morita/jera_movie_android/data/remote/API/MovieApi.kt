package com.morita.jera_movie_android.data.remote.API


import androidx.lifecycle.LiveData
import com.morita.jera_movie_android.data.remote.Model.Movie
import com.morita.jera_movie_android.data.remote.Response.MovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {
    @GET("/3/movie/popular")
   suspend fun getPopularMovie(@Query("api_key") api_key: String = API_KEY,): MovieResponse


    @GET("/3/movie/upcoming")
  suspend  fun getUpcoming(
        @Query("api_key") api_key: String = API_KEY,

        ): MovieResponse

    @GET("/movie/{movie_id}")
    fun getDetalhes(
        @Query("api_key") api_key: String = API_KEY
    ): MovieResponse


    companion object {
        val BASE_URL = "https://api.themoviedb.org"
        val API_KEY = "c45ebd84064ab6cf008d139d297f36cf"

    }
}
