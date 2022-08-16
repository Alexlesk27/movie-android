package com.morita.jera_movie_android

import android.app.Application
import com.morita.jera_movie_android.data.remote.dto.apiModulo
import com.morita.jera_movie_android.data.remote.dto.retrofitModule
import com.morita.jera_movie_android.data.remote.dto.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(
                retrofitModule,
                apiModulo,
                viewModelModule
            )
        }
    }
}