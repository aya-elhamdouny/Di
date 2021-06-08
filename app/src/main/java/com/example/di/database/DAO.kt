package com.example.di.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.di.model.Movie


@Dao
interface  DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: List<Movie>)

    @Query("select * from Movie")
    fun getMovies() : List<Movie>

    @Delete
    suspend fun deleteMovie(movie: List<Movie>)
}