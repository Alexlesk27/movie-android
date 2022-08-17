package com.morita.jera_movie_android.data.remote.Response
import com.google.gson.annotations.SerializedName
import com.morita.jera_movie_android.data.remote.Model.Movie

data class MovieResponse(
    @SerializedName("results")
    val Movie: List<Movie>


)