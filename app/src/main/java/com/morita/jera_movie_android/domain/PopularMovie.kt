package com.morita.jera_movie_android.domain

data class PopularMovie(
    val  id : Long,
    val title : String,
    val poster_path: String?,
    val  description: String,
    val voteAverage: Double
)
