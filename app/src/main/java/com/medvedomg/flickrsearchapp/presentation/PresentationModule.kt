package com.medvedomg.flickrsearchapp.presentation

import com.medvedomg.flickrsearchapp.presentation.main.MainViewModel
import com.medvedomg.flickrsearchapp.presentation.image_details.ImageDetailsViewModel
import com.medvedomg.flickrsearchapp.presentation.spots.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MainViewModel() }

    viewModel { SearchViewModel(getSpotsUseCase = get()) }

    viewModel { ImageDetailsViewModel() }
}