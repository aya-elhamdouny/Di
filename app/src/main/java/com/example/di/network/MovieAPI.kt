package com.example.di.network

import com.example.di.model.MovieResponse
import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend  fun getMovie(
            @Query("api_key") apiKey: String = "bf70bd0d200feb5b6281cd46a3bb40d2"
    ): Response<MovieResponse>

}