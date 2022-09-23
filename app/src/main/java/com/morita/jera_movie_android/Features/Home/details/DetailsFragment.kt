package com.morita.jera_movie_android.Features.Home.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.morita.jera_movie_android.Features.Home.HomeFragmentDirections
import com.morita.jera_movie_android.Features.Home.search.SearchFragmentDirections
import com.morita.jera_movie_android.Features.Home.setVisible
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.databinding.DetailsMovieBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_movie.*
import kotlinx.android.synthetic.main.search_movie.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNREACHABLE_CODE")
class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsMovieBinding
    private val detailsViewModel: DetailsViewModel by viewModel()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DetailsMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        detailsViewModel.getDetailsMovie(args.movieId)
        observeMovieDetails()

    }

    private fun observeMovieDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.apiDetailMovie.collect {
                    when (it) {
                        is ListState.Success -> {
                            movieDetails(it.value)
                            showLoading(false)

                        }

                        is ListState.Error -> {
                            showLoading(false)
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        }
                        is ListState.Loading -> {
                            Toast.makeText(
                                context,
                                "Carregando detalhes do filme  ",
                                Toast.LENGTH_SHORT
                            ).show()
                            showLoading(true)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun movieDetails(movie: Movie) {
        binding.releaseDate.text = movie.release_date
        binding.voteAverage.text = movie.voteAverage.toString()
        binding.titleDetail.text = movie.title
        binding.descripition.text = movie.description
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.backdrop_path)
            .into(backdrop_path);
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movie.poster_path)
            .into(image_detail);
    }

    private fun showLoading(state: Boolean) {
        binding.progressBarContainer.setVisible(state)
    }

}