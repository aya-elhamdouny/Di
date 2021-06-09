package com.example.di.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.di.database.DAO
import com.example.di.model.Movie

import com.example.di.model.MovieResponse
import com.example.di.network.MovieAPI
import com.example.di.repository.MovieRepository
import com.example.di.util.State
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val moviesList: MutableLiveData<State<MovieResponse>> by lazy {
        MutableLiveData<State<MovieResponse>>().also {     getMovie() }
    }
    lateinit var moviesdbList: MutableList<Movie>

    //api mood
      fun getMovie() = viewModelScope.launch {
         moviesList.postValue(State.Loading())
         val popularMovieResponse = repository.getMovie()
         moviesList.postValue(handleMovieResponse(popularMovieResponse))
    }

    fun handleMovieResponse(response: Response<MovieResponse>): State<MovieResponse>  {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                    return State.Success(resultResponse) }
        }
       return State.Error(response.message())
    }

    //database mood
   fun getMoviesFromDatabase()  = viewModelScope.launch {
        moviesdbList = repository.getMoviesFromDatabase()
    }




}