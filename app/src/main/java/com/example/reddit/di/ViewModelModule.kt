package com.example.reddit.di

import com.example.reddit.data.api.APIService
import com.example.reddit.data.api.RetrofitImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.reddit.presentation.view_models.SignInFragmentViewModel
import com.example.reddit.presentation.view_models.RegistrationFragmentViewModel
import com.example.reddit.presentation.view_models.HomeFragmentViewModel
import com.example.reddit.data.repository.PostsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf

val viewModelModule = module{
    viewModelOf(::SignInFragmentViewModel)
    viewModelOf(::RegistrationFragmentViewModel)
    viewModelOf(::HomeFragmentViewModel)
    singleOf(::PostsRepository)
    single<APIService> { RetrofitImpl.api }
}

//    singleOf(::SignInFragmentViewModel)
//    singleOf(::RegistrationFragmentViewModel)
//    singleOf(::HomeFragmentViewModel)


//viewModel { HomeFragmentViewModel(get()) }
//viewModelOf(::HomeFragmentViewModel)

//singleOf(::PostsRepository)