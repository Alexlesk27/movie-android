package com.morita.jera_movie_android.Models
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val poster_path: String?,
    val release_date: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("vote_average")
    val voteAverage: Double
):Parcelable
