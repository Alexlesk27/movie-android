package com.morita.jera_movie_android.data.remote.response

import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import com.morita.jera_movie_android.data.remote.dto.PopularMovieDto
import com.morita.jera_movie_android.data.remote.model.Movie
import com.morita.jera_movie_android.domain.PopularMovie

data class PopularMovieResponse(
    @SerializedName("results")
    val popularMovie: List<PopularMovieDto>

)
