package com.morita.jera_movie_android.Features.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.databinding.ItemMovieBinding


import com.squareup.picasso.Picasso


class UpcomingMoviesAdapter(

    private var context: Context,
    val onClick: (Movie) -> Unit

    ) : RecyclerView.Adapter<UpcomingMoviesAdapter.MoviesViewHolder>() {
    var item = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(context)
     val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val titulo = binding.title
        private val banner = binding.thumbnail


        fun bind(movie: Movie) {
            titulo.text = movie.title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.poster_path).into(banner);

            binding.root.setOnClickListener {
                onClick(movie)
            }
        }

    }

    fun submitList(lista: List<Movie>) {
        item = lista as ArrayList<Movie>
        notifyDataSetChanged()
    }

}