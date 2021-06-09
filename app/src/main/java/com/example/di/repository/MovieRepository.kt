package com.example.di.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.di.database.DAO
import com.example.di.model.Movie
import com.example.di.network.MovieAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepository@Inject constructor(
    private val movieDao: DAO,
    private val context: Context
) {

    lateinit var api : MovieAPI
    suspend fun getMovie() =
     api.getMovie()

    suspend fun getMoviesFromDatabase(): MutableList<Movie> {
        withContext(Dispatchers.IO) {
            val listOfMovies = getMovie()
            movieDao.deleteMovie()
            listOfMovies.body()?.movie?.let { movieDao.insert(it.toList()) }
        }
        return movieDao.getMovies()
    }




}