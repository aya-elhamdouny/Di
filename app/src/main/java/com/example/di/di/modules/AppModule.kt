package com.example.di.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.di.database.DAO
import com.example.di.database.MovieDataBase
import com.example.di.network.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
 class AppModule {


    @Provides
    @Singleton
    internal fun provideMovieApi(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun  provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build() }


    @Singleton
    @Provides
    fun provideDb(context: Context): MovieDataBase =
        Room.databaseBuilder(context, MovieDataBase::class.java, "movie")
            .fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideUserDao(db: MovieDataBase): DAO = db.movieDao()





    /**
     * Application application level context.
     */
    @Singleton
    @Provides
    fun provideContextInstance(application: Application): Context = application.applicationContext


    /**
     * Application resource provider, so that we can get the Drawable, Color, String etc at runtime
     */
    @Provides
    @Singleton
    fun providesResourcesInstance(application: Application): Resources = application.resources



    /**
     * Glide
     */
    @Singleton
    @Provides
    open fun provideGlideInstance(application: Application?, requestOptions: RequestOptions?): RequestManager? {
        return Glide.with(application!!)
                .setDefaultRequestOptions(requestOptions!!)
    }

}






