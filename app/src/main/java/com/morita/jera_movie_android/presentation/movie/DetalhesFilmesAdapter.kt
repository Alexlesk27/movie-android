//package com.morita.jera_movie_android.presentation.movie
//
//import android.os.Parcelable
//
//
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.morita.jera_movie_android.R
//import com.morita.jera_movie_android.domain.PopularMovie
//
//import com.squareup.picasso.Picasso
//import kotlinx.android.synthetic.main.detalhes_movie.view.*
//
//
//class DetalhesFilmesAdapter(
//    private  var context: Context
//
//) : RecyclerView.Adapter<DetalhesFilmesAdapter.DetalhesFilmesViewHolder1>() {
//    var item = arrayListOf<PopularMovie>()
//    override fun onCreateViewHolder(parent: ViewGroup, view: Int):DetalhesFilmesViewHolder1{
//        val layoutInflater = LayoutInflater.from(context)
//        val view = layoutInflater.inflate(R.layout.detalhes_movie, parent, false)
//        return DetalhesFilmesViewHolder1(view)
//    }
//
//
//    override fun onBindViewHolder(holder: DetalhesFilmesAdapter.DetalhesFilmesViewHolder1, position: Int) {
//        holder.bind(item[position])
//    }
//
//    override fun getItemCount(): Int {
//        return item.size
//    }
//
//
//     class DetalhesFilmesViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView), Parcelable {
//        private val titulo = itemView.findViewById<TextView>(R.id.title_detalhe)
//        private val sinopse = itemView.descripitio
//        private val banner = itemView.imagem_detalhe
//
//
//
//        fun bind(movie: PopularMovie) {
//            titulo.text = movie.title
//            sinopse.text = movie.description
//
//
//            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movie.poster_path).into(banner);
//
//        }
//
//
//
//
//    }
//
//    fun submitList(lista: List<PopularMovie>) {
//        item = lista as ArrayList<PopularMovie>
//        notifyDataSetChanged()
//    }
//
//
//
//
//}
