package com.example.gitapp.di

import com.example.gitapp.ui.characterList.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharacterViewModel(get())
    }

}