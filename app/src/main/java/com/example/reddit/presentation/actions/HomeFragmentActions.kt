package com.example.reddit.presentation.actions

import com.example.reddit.domain.model.Post

sealed class HomeFragmentActions {
    data object CheckSavedPosts: HomeFragmentActions()
    data object ReturnToRegistration: HomeFragmentActions()
    data object ReadFullPost: HomeFragmentActions()
    data object SavePost: HomeFragmentActions()
    //data class SetListOfPosts(val list: List<Post>) : HomeFragmentActions()
}