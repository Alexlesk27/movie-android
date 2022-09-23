package com.morita.jera_movie_android.data.remote.dto

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.morita.jera_movie_android.API.APIRest.MovieApi
import com.morita.jera_movie_android.API.APIRest.repository.MoviesRepository
import com.morita.jera_movie_android.Features.Home.UpcomingMovieViewModel
import com.morita.jera_movie_android.Features.Home.details.useCases.DetailUseCaseUseInterface
import com.morita.jera_movie_android.Features.Home.details.useCases.DetailUseMovieUseCase
import com.morita.jera_movie_android.Features.Home.popular.PopularMovieViewModel
import com.morita.jera_movie_android.Features.Home.popular.useCases.PopularMovieUseCase
import com.morita.jera_movie_android.Features.Home.popular.useCases.PopularMovieUseCaseInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val retrofitModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger.DEFAULT
        ).setLevel(
            HttpLoggingInterceptor.Level.HEADERS
        )
    }
    factory {
        OkHttpClient.Builder().addInterceptor(
            interceptor = get()
        ).build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

}


val usecaseModule = module {
    factory<PopularMovieUseCaseInterface> {
        PopularMovieUseCase(
            get()
        )
    }

    factory<DetailUseCaseUseInterface> {
       DetailUseMovieUseCase(
           get()
       )
    }
}

val apiModulo = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(MovieApi::class.java)
    }
}

//val viewModelSerchMovieModule = module {
//    viewModel {
//        SearchViewModel(
//            get ()
//        )
//    }
//}
//
//val viewModelSerchMovieModule = module {
//    viewModel {
//        SearchViewModel(
//            get ()
//        )
//    }
//}


val viewModelModule = module {
    viewModel {
        UpcomingMovieViewModel(
            get()
        )
    }
}


val viewPopularMovieModelModule = module {
    viewModel {
        PopularMovieViewModel(
            get()
        )
    }
}

val repositoryModule = module {
    single(createdAtStart = false) {
        MoviesRepository(get())
    }
}