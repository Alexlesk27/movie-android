package com.morita.jera_movie_android.Models
import com.google.gson.annotations.SerializedName
import com.morita.jera_movie_android.Models.Movie

data class MovieResponse(

    @SerializedName("results")
    val Movie: List<Movie>

)
