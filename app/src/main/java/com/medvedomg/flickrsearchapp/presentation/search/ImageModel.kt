package com.medvedomg.flickrsearchapp.presentation.search

import java.io.Serializable

data class ImageModel(
    val title: String,
    val description: String,
    val link: String,
    val author: String
) : Serializable {

    fun getSize(): String {
        val startWidth = description.substringAfterLast("width=\"")
        val width = startWidth.substringBefore("\"")
        val startHeight = description.substringAfterLast("height=\"")
        val height = startHeight.substringBefore("\"")
        return "Width: $width, Height: $height"
    }
}
