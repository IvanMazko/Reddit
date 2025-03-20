package com.example.reddit.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.domain.model.Post
import com.example.reddit.presentation.actions.HomeFragmentActions

class HomeFragmentViewModel : ViewModel() {

    data class CurrentState(
        val listOfPosts : List<Post> = emptyList(),
        val readFullPostBtn : Boolean = false,
        val savePostBtn : Boolean = false,
        val checkSavedPostsBtn : Boolean = false,
        val signOutBtn : Boolean = false
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        _liveData.value = CurrentState()
    }

    fun handleAction(action: HomeFragmentActions) {
        when(action) {
            is HomeFragmentActions.SetListOfPosts ->  _liveData.value = _liveData.value?.copy(listOfPosts = action.list)
            HomeFragmentActions.ReadFullPost -> _liveData.value = _liveData.value?.copy(readFullPostBtn = true)
            HomeFragmentActions.SavePost -> _liveData.value = _liveData.value?.copy(savePostBtn = true)
            HomeFragmentActions.CheckSavedPosts -> _liveData.value = _liveData.value?.copy(checkSavedPostsBtn = true)
            HomeFragmentActions.ReturnToRegistration -> _liveData.value = _liveData.value?.copy(signOutBtn = true)
        }
    }
}