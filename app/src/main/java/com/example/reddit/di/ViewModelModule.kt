package com.example.reddit.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.example.reddit.presentation.view_models.SignInFragmentViewModel

val viewModelModule = module{
    singleOf(::SignInFragmentViewModel)
    singleOf(::MainFragmentViewModel)
    singleOf(::NewNoteFragmentViewModel)
    singleOf(::RegistrationFragmentViewModel)
}