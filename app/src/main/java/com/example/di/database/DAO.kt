package com.example.di.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.di.model.Movie


@Dao
interface  DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: List<Movie>)

    @Query("select * from Movie")
    fun getMovies() : MutableList<Movie>

    @Delete
    suspend fun deleteMovie()

}