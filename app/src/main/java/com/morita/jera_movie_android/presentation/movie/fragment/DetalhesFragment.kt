package com.morita.jera_movie_android.presentation.movie.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.morita.jera_movie_android.R
import com.morita.jera_movie_android.databinding.DetalhesMovieBinding
import com.morita.jera_movie_android.presentation.MainViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetalhesFragment : Fragment(R.layout.detalhes_movie) {
    private var binding: DetalhesMovieBinding? = null
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)



    }

companion object{
    private const val EXTRA_TITLE ="EXTRA_TITLE"
    private const val EXTRA_DESCRIPTION ="EXTRA_DESCRIPTION"
    fun getStarIntent(context: Context, title:String ,description:String  ):Intent{
        return Intent(context, DetalhesFragment::class.java).apply {
            putExtra(EXTRA_TITLE, title)
            putExtra(EXTRA_DESCRIPTION, description)

        }
    }
}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetalhesMovieBinding.inflate(inflater, container, false)
        return binding!!.root
    }



    }

