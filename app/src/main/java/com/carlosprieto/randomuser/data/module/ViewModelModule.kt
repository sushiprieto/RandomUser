package com.carlosprieto.randomuser.data.module

import com.carlosprieto.randomuser.view.home.HomeViewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { HomeViewViewModel() }
}