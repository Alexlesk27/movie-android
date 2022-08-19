package com.morita.jera_movie_android.presentation.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.data.remote.Model.Movie


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesUpcomingAdapter(
    private var context: Context,


    ) : RecyclerView.Adapter<MoviesUpcomingAdapter.MoviesViewHolder>() {
    var item = arrayListOf<Movie>()


    override fun onCreateViewHolder(parent: ViewGroup, view: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titulo = itemView.title
        private val banner = itemView.thumbnail

        fun bind(movie: Movie) {

            titulo.text = movie.title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.poster_path).into(banner);

        }

    }

    fun submitList(lista: List<Movie>) {
        item = lista as ArrayList<Movie>
        notifyDataSetChanged()
    }

}