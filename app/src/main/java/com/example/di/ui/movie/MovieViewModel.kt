package com.example.di.ui.movie

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.di.di.base.BaseApplication
import com.example.di.model.MovieResponse
import com.example.di.network.MovieAPI
import com.example.di.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val application: Application) : ViewModel() {


    @Inject
    lateinit var movieApi : MovieAPI

    val moviesList: MutableLiveData<MovieResponse> by lazy {
        MutableLiveData<MovieResponse>().also {     getMovie() }
    }
    var movieResponse : MovieResponse? = null

    fun getMovie() = viewModelScope.launch {
        val popularMovieResponse = movieApi.getMovie()
        moviesList.postValue(handleMovieResponse(popularMovieResponse))
    }

    private fun handleMovieResponse(response: Response<MovieResponse>): MovieResponse? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                    return resultResponse }
        }
       return movieResponse
    }


}