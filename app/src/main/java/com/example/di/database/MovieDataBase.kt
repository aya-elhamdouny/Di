package com.example.di.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.di.model.Movie


@Database(entities = [Movie::class] , version = 1)
abstract  class MovieDataBase : RoomDatabase() {
    abstract fun movieDao() : DAO
}