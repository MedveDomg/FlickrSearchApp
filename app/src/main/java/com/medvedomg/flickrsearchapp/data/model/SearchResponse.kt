package com.medvedomg.flickrsearchapp.data.model

import com.squareup.moshi.Json

data class SearchResponse(
    @field:Json(name = "items")
    val items: List<ImageResponse>,
)

data class ImageResponse(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "link")
    val link: String,
    @field:Json(name = "author")
    val author: String,
    @field:Json(name = "media")
    val mediaResponse: MediaResponse
)

data class MediaResponse(
    @field:Json(name = "m")
    val url: String
)