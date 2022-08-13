package com.medvedomg.flickrsearchapp.data

import com.medvedomg.flickrsearchapp.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun search(
        @Query("tags") tags: String,
    ): SearchResponse
}