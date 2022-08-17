package com.morita.jera_movie_android.data.remote.Model
import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long,
    val title: String,
    val poster_path: String?,
    val description: String,
    @SerializedName("vote_average")
    val voteAverage: Double
) {
    fun toMovie(): Movie {
        return Movie(
            id = id,
            title = title,
            poster_path = poster_path,
            description = description,
            voteAverage = voteAverage,
        )
    }
}
