package com.example.di.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Movie")
data class Movie constructor(
    val backdrop_path: String,
    @PrimaryKey
    @SerializedName("movie_id")
    val id: Int,
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val isFav: Boolean
): Serializable
