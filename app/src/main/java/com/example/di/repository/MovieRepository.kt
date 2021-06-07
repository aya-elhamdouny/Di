package com.example.di.repository

import com.example.di.network.MovieAPI
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
class MovieRepository(){


    lateinit var api : MovieAPI
    suspend fun getMovie() =
     api.getMovie()

}