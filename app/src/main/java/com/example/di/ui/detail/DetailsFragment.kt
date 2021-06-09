package com.example.di.ui.detail
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.di.R
import com.example.di.model.Movie
import com.example.di.ui.movie.MovieAdapter
import com.example.di.ui.movie.MovieFragmentDirections
import com.example.di.ui.movie.MovieViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.details_fragment.*
import javax.inject.Inject

class DetailsFragment : DaggerFragment(R.layout.details_fragment) {
    lateinit var viewModel: MovieViewModel
    lateinit var movielist: List<Movie>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var adapter: MovieAdapter
    lateinit var requestManager: RequestManager
    val args: DetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)
        val movie = args.movie

        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToDetailsFragment(it)
            )
        }

        movie_poster.apply {
            requestManager.load("https://image.tmdb.org/t/p/w1280${movie.poster}")
                .transform(CenterCrop())
                .into(this)
        }


        movie_backdrop.apply {
            requestManager.load("https://image.tmdb.org/t/p/w1280${movie.backdrop_path}")
                .transform(CenterCrop())
                .into(this)

        }
        movie_title.text = movie.title
        movie_overview.text = movie.overview
        movie_release_date.text = movie.release_date

    }
}