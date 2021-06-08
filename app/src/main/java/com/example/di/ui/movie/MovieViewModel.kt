package com.example.di.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.di.database.DAO
import com.example.di.model.Movie

import com.example.di.model.MovieResponse
import com.example.di.network.MovieAPI
import com.example.di.util.State
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieApi: MovieAPI , private val moviedao : DAO) : ViewModel() {

    val moviesList: MutableLiveData<State<MovieResponse>> by lazy {
        MutableLiveData<State<MovieResponse>>().also {     getMovie() }
    }

        //api mood
      fun getMovie() = viewModelScope.launch {
         moviesList.postValue(State.Loading())
         val popularMovieResponse = movieApi.getMovie()
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
    suspend fun getFromDatabase(response: Response<MovieResponse>) : List<Movie> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                moviedao.deleteMovie(result.movie)
                moviedao.insert(result.movie)

            }
        }
        return moviedao.getMovies()
    }



}