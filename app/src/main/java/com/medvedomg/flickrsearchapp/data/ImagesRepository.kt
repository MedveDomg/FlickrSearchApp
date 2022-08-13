package com.medvedomg.flickrsearchapp.data

import com.medvedomg.flickrsearchapp.data.model.SearchResponse

interface ImagesRepository {

    suspend fun getImages(tags: String): SearchResponse
}

class ImageRepositoryImpl(
    val apiService: SearchApiService
) : ImagesRepository {

    override suspend fun getImages(tags: String): SearchResponse {
        return apiService.search(tags)
    }
}