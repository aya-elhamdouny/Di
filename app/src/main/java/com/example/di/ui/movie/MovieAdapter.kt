package com.example.di.ui.movie

import com.example.di.R
import com.example.di.model.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop

import com.google.android.material.imageview.ShapeableImageView


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {



    private lateinit var movies : List<Movie>
    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }


    private val differCallback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
    private var onItemClickListener : ((Movie) -> Unit)? = null

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = differ.currentList[position]
        holder.itemView.apply {

            val poster: ShapeableImageView = findViewById(R.id.movie_poster)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w342${movie.poster}")
                .transform(CenterCrop())
                .into(poster)
            setOnClickListener {
                onItemClickListener?.let {
                    it(movie)
                }
            }

        }
    }

    fun setOnItemClickListener(listener: ((Movie) -> Unit)){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
