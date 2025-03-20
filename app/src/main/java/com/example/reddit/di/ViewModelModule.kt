package com.example.reddit.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.reddit.presentation.view_models.SignInFragmentViewModel
import com.example.reddit.presentation.view_models.RegistrationFragmentViewModel
import com.example.reddit.presentation.view_models.MainFragmentViewModel

val viewModelModule = module{
    singleOf(::SignInFragmentViewModel)
    singleOf(::RegistrationFragmentViewModel)
    singleOf(::MainFragmentViewModel)
}