package com.medvedomg.flickrsearchapp.data

import okhttp3.OkHttpClient
import org.koin.dsl.module
import java.util.concurrent.TimeUnit
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal val dataModule = module {

    single {
        val builder = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
        builder.build()
    }

    factory {
        Moshi.Builder().build()
    }

    single {
        val okHttpClient: OkHttpClient = get()
        val moshi: Moshi = get()
        val converterFactory = MoshiConverterFactory.create(moshi)
        Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/")
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    single<SearchApiService> {
        val retrofit: Retrofit = get()
        retrofit.create(SearchApiService::class.java)
    }

    single<ImagesRepository> {
        ImageRepositoryImpl(
            apiService = get()
        )
    }
}