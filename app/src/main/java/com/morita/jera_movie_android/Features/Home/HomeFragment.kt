package com.morita.jera_movie_android.Features.Home

import ListState
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.databinding.FragmentMoviePopularBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNUSED_EXPRESSION")
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentMoviePopularBinding
    private lateinit var moviesUpcomingAdapter: UpcomingMoviesAdapter
    private lateinit var moviePopularAdapter: MoviesPopularAdapter
    private val  popularMovieViewModel : PopularMovieViewModel by viewModel()
    private val mainViewModel:UpcomingMovieViewModel by viewModel()

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
        observeMoviePopular()
        observeMovieUpcoming()

        val popularMovieRecyclerView = binding!!.recicyclerFilmesPopulares
        popularMovieRecyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        moviePopularAdapter = MoviesPopularAdapter(requireContext()) {
            goToDetailMovie(it)
        }
        popularMovieRecyclerView.adapter = moviePopularAdapter

        val upcomingRecyclerView = binding!!.recicyclerFilmesPorvir
        upcomingRecyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        moviesUpcomingAdapter = UpcomingMoviesAdapter(requireContext()) {
            goToDetailMovie(it)
        }
        upcomingRecyclerView.adapter = moviesUpcomingAdapter

    }

    private fun observeMoviePopular() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                popularMovieViewModel.apiPopularMovie.collect() {
                    when (it) {
                        is ListState.Success -> {
                            showLoading(false)
                            moviePopularAdapter.submitList(it.value)
                        }
                        is ListState.Error -> {
                            showLoading(false)
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        }
                        is ListState.Loading -> handleLoadingState()
                        else -> {}
                    }
                }
            }
        }
    }

    private fun observeMovieUpcoming() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.apiMovieUpcoming.collect {
                    when (it) {
                        is ListState.Success -> {
                            showLoading(false)
                            moviesUpcomingAdapter.submitList(it.value)
                        }
                        is ListState.Error -> {
                            showLoading(false)
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        }
                        is ListState.Loading -> handleLoadingState()

                        else -> {}
                    }
                }
            }
        }
    }

    private fun goToDetailMovie(movie: Movie) {
        val action =
            HomeFragmentDirections.actionFragmentMoviePopular3ToDetalhesFragment32(movie)
        Log.i("movie", "$movie")
        findNavController().navigate(action)
    }


    private fun handleLoadingState() {
        showLoading(true)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBarContainer.setVisible(state)
    }

}

