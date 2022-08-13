package com.medvedomg.flickrsearchapp.presentation.spots

import java.io.Serializable

data class ImageModel(
    val title: String,
    val description: String,
    val link: String,
    val author: String
) : Serializable
