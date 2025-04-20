package com.example.reddit.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.reddit.data.api.objects.PostResponse
import com.example.reddit.data.repository.PostsRepository
import com.example.reddit.domain.model.Post
import com.example.reddit.presentation.actions.HomeFragmentActions
import com.example.reddit.presentation.view.PostsPagingSource

class HomeFragmentViewModel(private val repository: PostsRepository) : ViewModel() {

    data class CurrentState(
        val listOfPosts: LiveData<PagingData<PostResponse>> = MutableLiveData(),
        val readFullPostBtn : Boolean = false,
        val savePostBtn : Boolean = false,
        val checkSavedPostsBtn : Boolean = false,
        val signOutBtn : Boolean = false
    )

    private val _liveData = MutableLiveData<CurrentState>()
    val liveData: LiveData<CurrentState> get() = _liveData

    init {
        _liveData.value = CurrentState(listOfPosts = repository.getPosts())
    }

    fun handleAction(actions: HomeFragmentActions) {
        when(actions) {
            HomeFragmentActions.ReadFullPost -> _liveData.value = _liveData.value?.copy(readFullPostBtn = true)
            HomeFragmentActions.SavePost -> _liveData.value = _liveData.value?.copy(savePostBtn = true)
            HomeFragmentActions.CheckSavedPosts -> _liveData.value = _liveData.value?.copy(checkSavedPostsBtn = true)
            HomeFragmentActions.ReturnToRegistration -> _liveData.value = _liveData.value?.copy(signOutBtn = true)
        }
    }
}


//is HomeFragmentActions.SetListOfPosts ->  _liveData.value = _liveData.value?.copy(listOfPosts = actions.list)