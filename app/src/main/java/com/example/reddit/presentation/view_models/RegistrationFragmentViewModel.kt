package com.example.reddit.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reddit.data.models.User
import com.example.reddit.data.storage.room.UserDao
import com.example.reddit.presentation.actions.RegistrationFragmentActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationFragmentViewModel : ViewModel() {
    data class CurrentState(
        val toSignInScreenBtn : Boolean = false,
        val toMainScreenBtn : Boolean = false,
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        _liveData.value = CurrentState()
    }

    fun handleAction(actions: RegistrationFragmentActions){
        when(actions){
            is RegistrationFragmentActions.GoToSignInScreen -> _liveData.value = _liveData.value?.copy(toSignInScreenBtn = true)
            is RegistrationFragmentActions.GoToMainScreen -> _liveData.value = _liveData.value?.copy(toMainScreenBtn = true)
            is RegistrationFragmentActions.RegisterUser -> registerUser(actions.userName, actions.password, actions.dao)
        }
    }

    private fun registerUser(userName : String, userPassword : String, dao: UserDao){
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertUser(User(0, username = userName, password = userPassword))
        }
    }
}