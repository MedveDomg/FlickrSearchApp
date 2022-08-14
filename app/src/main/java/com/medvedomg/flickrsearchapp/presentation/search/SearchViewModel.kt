package com.medvedomg.flickrsearchapp.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medvedomg.flickrsearchapp.domain.GetImagesUseCase
import com.medvedomg.flickrsearchapp.presentation.util.ViewState
import com.medvedomg.flickrsearchapp.presentation.util.asLiveData
import kotlinx.coroutines.launch
import com.medvedomg.flickrsearchapp.domain.util.Result

class SearchViewModel(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    private val _viewStateLiveData =
        MutableLiveData<ViewState<List<ImageModel>>>(ViewState.Loading)
    val viewStateLiveData = _viewStateLiveData.asLiveData()

    fun loadData(tags: String) {
        viewModelScope.launch {
            when (val result = getImagesUseCase(tags)) {
                is Result.Success -> {
                    val list = result.data.items.map {
                        ImageModel(
                            title = it.title,
                            description = it.description,
                            link = it.mediaResponse.url,
                            author = it.author
                        )
                    }
                    val viewState = ViewState.Success(list)
                    _viewStateLiveData.postValue(viewState)
                }
                is Result.Error -> {
                    val viewState = ViewState.Error<List<ImageModel>>(
                        error = result.error,
                        errorMessage = result.error.message
                    )
                    _viewStateLiveData.postValue(viewState)
                }
            }
        }
    }

    fun startLoading() {
        _viewStateLiveData.postValue(ViewState.Loading)
    }
}