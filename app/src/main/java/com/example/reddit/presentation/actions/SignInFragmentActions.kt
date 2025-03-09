package com.example.reddit.presentation.actions

sealed class SignInFragmentActions {
    data object GoToRegistrationScreen : SignInFragmentActions()
    data object GoToMainScreen : SignInFragmentActions()
}