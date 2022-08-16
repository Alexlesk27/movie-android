package com.morita.jera_movie_android.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.morita.jera_movie_android.domain.PopularMovie

data class PopularMovieDto(
    val id : Long,
    val title: String,
    val poster_path: String?,
    val overview: String,
    @SerializedName("release_date")
    val  realeseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double

){
    fun toPopularMovie(): PopularMovie{
      return PopularMovie(
       id=id,
       title=title,
       description = overview,
       voteAverage= voteAverage,
          poster_path = poster_path
      )
    }
}



