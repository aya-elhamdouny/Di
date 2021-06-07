package com.example.di.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.di.model.MovieResponse
import com.example.di.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {



    val moviesList: MutableLiveData<MovieResponse> by lazy {
        MutableLiveData<MovieResponse>().also {     getMovie() }
    }
    var movieResponse : MovieResponse? = null

    fun getMovie() = viewModelScope.launch {
        //moviesList.postValue(Resource.())
        val popularMovieResponse = movieRepository.getMovie()
        moviesList.postValue(handleMovieResponse(popularMovieResponse))
    }

    private fun handleMovieResponse(response: Response<MovieResponse>): MovieResponse? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                    return movieResponse }
        }
       return movieResponse
    }


}