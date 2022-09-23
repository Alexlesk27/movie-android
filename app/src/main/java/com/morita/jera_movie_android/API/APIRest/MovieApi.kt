package com.morita.jera_movie_android.API.APIRest


import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.Models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    @GET("/3/movie/popular")
    suspend fun getPopularMovie(@Query("api_key") api_key: String = API_KEY): MovieResponse


    @GET("/3/movie/upcoming")
  suspend  fun getUpcoming(
        @Query("api_key") api_key: String = API_KEY,

        ): MovieResponse

    @GET("3/movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String = API_KEY,

        ): Movie


    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
        const val API_KEY ="c45ebd84064ab6cf008d139d297f36cf"

    }
}
