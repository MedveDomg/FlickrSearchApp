package com.medvedomg.flickrsearchapp

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.medvedomg.flickrsearchapp.data.dataModule
import com.medvedomg.flickrsearchapp.domain.domainModule
import com.medvedomg.flickrsearchapp.presentation.presentationModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .availableMemoryPercentage(0.25)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }.build()
    }


    companion object {
        val appModules = mutableListOf(
            dataModule,
            domainModule,
            presentationModule
        )
    }
}