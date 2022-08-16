package com.morita.jera_movie_android.presentation.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.domain.PopularMovie
import com.morita.jera_movie_android.presentation.FragmentMoviePopular

import com.squareup.picasso.Picasso


 class FilmesAdapterM(
private  var context: Context

) : RecyclerView.Adapter<FilmesAdapterM.FilmesViewHolder>() {
    var item = arrayListOf<PopularMovie>()
    override fun onCreateViewHolder(parent: ViewGroup, view: Int): FilmesViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.item_movie, parent, false)
        return FilmesViewHolder(view)
    }


    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        holder.bind(item[position])


    }

    override fun getItemCount(): Int {
        return item.size
    }


    class FilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titulo = itemView.findViewById<TextView>(R.id.title)
        private val banner = itemView.findViewById<ImageView>(R.id.thumbnail)

        fun bind(movie: PopularMovie) {
            titulo.text = movie.title
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.poster_path).into(banner);

       }


    }

    fun submitList(lista: List<PopularMovie>) {
        item = lista as ArrayList<PopularMovie>
        notifyDataSetChanged()
    }


}
