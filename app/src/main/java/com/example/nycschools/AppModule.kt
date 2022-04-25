package com.example.nycschools

import androidx.annotation.Keep
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@Keep
val appModule = module {
    viewModel { SchoolsListViewModel() }
    viewModel { SchoolDetailsViewModel() }
}
