package com.example.reddit.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.presentation.actions.SignInFragmentActions

class SignInFragmentViewModel : ViewModel() {

    data class CurrentState(
        val toRegisterScreenBtn : Boolean = false,
        val toMainScreenBtn : Boolean = false
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        _liveData.value = CurrentState()
    }

    fun handleAction(actions: SignInFragmentActions){
        when(actions){
            is SignInFragmentActions.GoToRegistrationScreen -> _liveData.value = _liveData.value?.copy(toRegisterScreenBtn = true)
            is SignInFragmentActions.GoToMainScreen -> _liveData.value = _liveData.value?.copy(toMainScreenBtn = true)
        }
    }
}