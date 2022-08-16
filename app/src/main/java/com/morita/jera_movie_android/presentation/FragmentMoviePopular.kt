package com.morita.jera_movie_android.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.databinding.ActivityMainBinding
import com.morita.jera_movie_android.databinding.FragmentMoviePopularBinding
import com.morita.jera_movie_android.presentation.movie.FilmesAdapterM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentMoviePopular : Fragment(R.layout.fragment_movie_popular) {
    private val context: FragmentMoviePopular? = null
    private var binding: FragmentMoviePopularBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var filmesAdapterM: FilmesAdapterM
    private val mainViewModel: MainViewModel by viewModel()

    companion object {
        fun newInstance() = FragmentMoviePopular()

    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        callPopularMovie()

        val recyclerView = binding!!.recicyclerFilmesPopulares
        recyclerView.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        filmesAdapterM = FilmesAdapterM(requireContext())
        recyclerView.adapter = filmesAdapterM


        mainViewModel.popularMovie.observe(viewLifecycleOwner) { popularMovie ->
          popularMovie?.let {
               filmesAdapterM.submitList(it)

            }

      }

        val recyclerView2 = binding!!.recicyclerFilmesPopulares
        recyclerView2.layoutManager =
            LinearLayoutManager(activity, GridLayoutManager.HORIZONTAL, false)
        filmesAdapterM = FilmesAdapterM(requireContext())
        recyclerView2.adapter = filmesAdapterM


        mainViewModel.popularMovie.observe(viewLifecycleOwner) { popularMovie ->
            popularMovie?.let {
                filmesAdapterM.submitList(it)

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


    private fun callPopularMovie() {
        GlobalScope.launch {
            mainViewModel.getpopularMovie()
            mainViewModel.getPorVir()
        }
    }





}











