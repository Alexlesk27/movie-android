package com.morita.jera_movie_android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.databinding.ActivityMainBinding
import com.morita.jera_movie_android.presentation.movie.fragment.FragmentMoviePopular
import com.morita.jera_movie_android.presentation.movie.adapter.MoviesPopularAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var filmesAdapterM: MoviesPopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragmentMoviePopular = FragmentMoviePopular()
        val fragmentMenager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentMenager.beginTransaction()
        fragmentTransaction.replace(R.id.conteiner_main, fragmentMoviePopular).commit()


    }
}