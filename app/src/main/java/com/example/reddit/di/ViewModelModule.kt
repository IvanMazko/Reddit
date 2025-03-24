package com.example.reddit.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.reddit.presentation.view_models.SignInFragmentViewModel
import com.example.reddit.presentation.view_models.RegistrationFragmentViewModel
import com.example.reddit.presentation.view_models.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module{
    singleOf(::SignInFragmentViewModel)
    singleOf(::RegistrationFragmentViewModel)
    //singleOf(::HomeFragmentViewModel)
    viewModel { HomeFragmentViewModel(get()) }
}