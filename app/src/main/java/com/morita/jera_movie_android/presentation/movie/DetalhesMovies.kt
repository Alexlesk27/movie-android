//package com.morita.jera_movie_android.presentation.movie
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.morita.jera_movie_android.R
//import com.morita.jera_movie_android.databinding.DetalhesMovieBinding
//import com.morita.jera_movie_android.presentation.FragmentMoviePopular
//
//class DetalhesMovies : Fragment(R.layout.detalhes_movie) {
//    private var binding: DetalhesMovieBinding? = null
//    private lateinit var filmesAdapterM: FilmesAdapterM
//    private lateinit var filmesPorVirViewHolder: FilmesPorvirAdapter1
//
//    companion object {
//        fun newInstance() = DetalhesMovies()
//
//    }
//
//
//    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(itemView, savedInstanceState)
//
//
//
//        val recyclerView = binding!!.
//        recyclerView.layoutManager =
//            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
//        filmesAdapterM = FilmesAdapterM(requireContext())
//        recyclerView.adapter = filmesAdapterM
//}