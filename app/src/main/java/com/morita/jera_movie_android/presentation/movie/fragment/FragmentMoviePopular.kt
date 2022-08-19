package com.morita.jera_movie_android.presentation.movie.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.morita.jera_movie_android.databinding.FragmentMoviePopularBinding
import com.morita.jera_movie_android.presentation.MainViewModel
import com.morita.jera_movie_android.presentation.movie.adapter.MoviesUpcomingAdapter
import com.morita.jera_movie_android.presentation.movie.adapter.MoviesPopularAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


@Suppress("UNUSED_EXPRESSION")
class FragmentMoviePopular : Fragment() {
   private  lateinit  var binding: FragmentMoviePopularBinding
    private lateinit var moviesUpcomingAdapter: MoviesUpcomingAdapter
    private lateinit var noviePopularAdapter: MoviesPopularAdapter
    private val mainViewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = FragmentMoviePopular()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMoviePopularBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        observeMovie()
        observeMovie2()

        val recyclerView = binding!!.recicyclerFilmesPopulares
        recyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        noviePopularAdapter = MoviesPopularAdapter(requireContext())
        recyclerView.adapter = noviePopularAdapter


        val recyclerView2 = binding!!.recicyclerFilmesPorvir
        recyclerView2.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        moviesUpcomingAdapter = MoviesUpcomingAdapter(requireContext())
        recyclerView2.adapter = moviesUpcomingAdapter

    }


    private fun observeMovie() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.apiMoviePopularState.collect {
                    when (it) {
                        is ListState.Success -> {noviePopularAdapter.submitList(it.value)}
                        is ListState.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    private fun observeMovie2() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.apiMovieUpcomingState.collect {
                    when (it) {
                        is ListState.Success -> {moviesUpcomingAdapter.submitList(it.value)}
                        is ListState.Error -> {}
                        else -> {}
                    }
                }
            }
        }
    }
}