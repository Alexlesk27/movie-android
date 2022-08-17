package com.morita.jera_movie_android.data.remote.API


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.morita.jera_movie_android.data.remote.Response.MovieResponse as MovieResponse1

interface MovieApi {
    @GET("/3/movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieResponse1>


    @GET("/3/movie/upcoming")
    fun getUpcoming(
        @Query("api_key") api_key: String = API_KEY,

        ): Call<MovieResponse1>

    @GET("/movie/{movie_id}")
    fun getDetalhes(
        @Query("api_key") api_key: String = API_KEY
    ): Call<MovieResponse1>


    companion object {
        val BASE_URL = "https://api.themoviedb.org"
        val API_KEY = "c45ebd84064ab6cf008d139d297f36cf"

    }
}