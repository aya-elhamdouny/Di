package com.example.di.di.modules

import android.content.Context
import androidx.room.Room
import com.example.di.database.DAO
import com.example.di.database.MovieDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {


    @Provides
    @Singleton
    fun ProvideRoom(context : Context) : MovieDataBase{
        return Room.databaseBuilder(context , MovieDataBase::class.java , "Movie")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideDao(movieDataBase: MovieDataBase) : DAO{
        return movieDataBase.movieDao()
    }
}