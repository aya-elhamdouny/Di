package com.example.di.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.di.R
import com.example.di.model.Movie
import com.example.di.util.State
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.movie_fragment.*
import javax.inject.Inject


class MovieFragment : DaggerFragment(R.layout.movie_fragment) {

    val TAG = "error"
    lateinit var viewModel: MovieViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var adapter: MovieAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        setMovieRecyclerView()

       if(true){
          adapter.differ.submitList( viewModel.moviesdbList)
       }
       else{ viewModel.moviesList.observe(viewLifecycleOwner, Observer { response ->
           when (response) {
               is State.Success -> {
                   response.data?.let { movieResponse ->
                       adapter.differ.submitList(movieResponse.movie)
                   }
               }
               is State.Error -> {
                   response.msg?.let { message ->
                       Log.e(TAG, "error : $message")
                   }
               }
           }
       })}



    }


    private fun setMovieRecyclerView() {
       adapter = MovieAdapter()
        movie_rv.apply {
         adapter= adapter
         layoutManager =LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

    }

}

