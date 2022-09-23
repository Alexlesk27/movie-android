package com.morita.jera_movie_android.Features.Home.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.databinding.ItemMovieBinding
import com.morita.jera_movie_android.databinding.ItemSearchBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class SearchMovieAdapter(
    private var context: Context,
    val onclick: (Movie)->Unit
) : RecyclerView.Adapter<SearchMovieAdapter.SerchViewHolder>() {
    var item = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerchViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
        return SerchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SerchViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int {
        return item.size
    }

    inner class SerchViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val titulo = binding.title
        private val banner = binding.thumbnail


        fun bind(movie: Movie) {
            titulo.text = movie.title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.poster_path).into(banner);

            binding.root.setOnClickListener {
                onclick(movie)
            }
        }




    }
    fun submitList(list: List<Movie>) {
        item = list as ArrayList<Movie>
        notifyDataSetChanged()
    }
}




