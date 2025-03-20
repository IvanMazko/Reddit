package com.example.reddit.presentation.actions

import com.example.reddit.domain.model.Post

sealed class MainFragmentActions {
    data object CheckSavedPosts: MainFragmentActions()
    data object ReturnToRegistration: MainFragmentActions()
    data object ReadFullPost: MainFragmentActions()
    data object SavePost: MainFragmentActions()
    data class SetListOfPosts(val list: List<Post>) : MainFragmentActions()
}