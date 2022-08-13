package com.medvedomg.flickrsearchapp.domain

import com.medvedomg.flickrsearchapp.data.ImagesRepository
import com.medvedomg.flickrsearchapp.data.model.SearchResponse
import com.medvedomg.flickrsearchapp.domain.util.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetImagesUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    val imagesRepository: ImagesRepository
) : CoroutineUseCase<String, SearchResponse>(coroutineDispatcher) {

    override suspend fun execute(parameters: String): SearchResponse {
        return imagesRepository.getImages(parameters)
    }
}