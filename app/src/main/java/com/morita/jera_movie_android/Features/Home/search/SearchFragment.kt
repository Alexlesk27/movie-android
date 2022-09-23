package com.morita.jera_movie_android.Features.Home.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.morita.jera_movie_android.Features.Home.setVisible
import com.morita.jera_movie_android.Models.Movie
import com.morita.jera_movie_android.databinding.SearchMovieBinding
import kotlinx.android.synthetic.main.details_movie.*
import kotlinx.android.synthetic.main.search_movie.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment() : Fragment() {
    private lateinit var binding: SearchMovieBinding
    private lateinit var searchMovieAdapter: SearchMovieAdapter
    private val searchViewModel: SearchViewModel by viewModel()
    var item = arrayListOf<Movie>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchView()
        observeMovieSearch()



        val searchRecyclerView = binding.recicyclerSearch
        searchRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        searchMovieAdapter = SearchMovieAdapter(requireContext()) {
            goToDetailMovie(it)
        }
        searchRecyclerView.adapter = searchMovieAdapter

    }


    private fun observeMovieSearch() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchViewModel.apiSearchMovie.collect {
                    when (it) {
                        is ListState.Success -> {
                            showLoading(false)
                            searchMovieAdapter.submitList(it.value)
                        }
                        is ListState.Error -> {
                            showLoading(false)
                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                        }
                        is ListState.Loading -> {
                            showLoading(true)
                        }
                        is ListState.Empty -> {
                            showLoading(false)
                            Toast.makeText(context, "Vazio", Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }
            }
        }
    }


    fun initSearchView() {
        binding.searchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val searchString = search_movie.query.toString()
                searchViewModel.getSearchMovie(searchString)
                search_movie.clearFocus()
                Log.i("tag", "$searchString")

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                newText?.let { newText ->
                    searchViewModel.getSearchMovie(newText)
                }
                return true
            }

        })

    }

    private fun goToDetailMovie(movie: Movie) {
        val action = SearchFragmentDirections.actionSearchToDetalhesFragment3(movie.id)
        findNavController().navigate(action)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchMovieBinding.inflate(inflater, container, false)
        return binding.root

    }


    private fun showLoading(state: Boolean) {
        binding.progressBarContainer.setVisible(state)
    }

}

