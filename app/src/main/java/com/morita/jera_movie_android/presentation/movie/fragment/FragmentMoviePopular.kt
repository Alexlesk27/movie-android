package com.morita.jera_movie_android.presentation.movie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.databinding.FragmentMoviePopularBinding
import com.morita.jera_movie_android.presentation.MainViewModel
import com.morita.jera_movie_android.presentation.movie.adapter.MoviesUpcomingAdapter
import com.morita.jera_movie_android.presentation.movie.adapter.MoviesPopularAdapter

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentMoviePopular : Fragment(R.layout.fragment_movie_popular) {
    private val context: FragmentMoviePopular? = null
    private var binding: FragmentMoviePopularBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var moviesUpcomingAdapter: MoviesUpcomingAdapter
    private lateinit var filmesAdapterM: MoviesPopularAdapter

    private val mainViewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = FragmentMoviePopular()

    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        callPopularMovie2()


        val recyclerView = binding!!.recicyclerFilmesPopulares
        recyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        filmesAdapterM = MoviesPopularAdapter(requireContext())
        recyclerView.adapter = filmesAdapterM


        val recyclerView2 = binding!!.recicyclerFilmesPorvir
        recyclerView2.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        moviesUpcomingAdapter = MoviesUpcomingAdapter(requireContext())
        recyclerView2.adapter = moviesUpcomingAdapter




        mainViewModel.popularMovie.observe(viewLifecycleOwner) { popularMovie ->
            popularMovie?.let {
                filmesAdapterM.submitList(it)

            }

        }


        mainViewModel.povir.observe(viewLifecycleOwner) { popularMovie ->
            popularMovie?.let {
                moviesUpcomingAdapter.submitList(it)

            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviePopularBinding.inflate(inflater, container, false)

        return binding!!.root

    }



    private fun callPopularMovie2() {
        GlobalScope.launch {
            mainViewModel.getPorVir()

        }
    }

}