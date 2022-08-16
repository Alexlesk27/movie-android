package com.morita.jera_movie_android.data.remote.dto

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.morita.jera_movie_android.data.remote.MovieApi
import com.morita.jera_movie_android.presentation.MainViewModel
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


val apiModulo = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(MovieApi::class.java)
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(
            get()
        )
    }
}
