package com.medvedomg.flickrsearchapp.presentation.main

import androidx.lifecycle.ViewModel
import com.medvedomg.flickrsearchapp.presentation.util.SingleLiveEvent
import com.medvedomg.flickrsearchapp.presentation.util.asLiveData

class MainViewModel : ViewModel() {

    private val navigationActionMutableLiveData = SingleLiveEvent<Action>()
    val navigationActionLiveData = navigationActionMutableLiveData.asLiveData()

    init {
        navigationActionMutableLiveData.value = Action.StartAction
    }

    sealed class Action {

        object StartAction : Action()
    }

}