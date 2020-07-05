package com.raywenderlich.android.insertkoin.di

import com.raywenderlich.android.insertkoin.repository.RemoteRepository
import com.raywenderlich.android.insertkoin.repository.Repository
import com.raywenderlich.android.insertkoin.ui.MainViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val mainModule = applicationContext {
    viewModel { MainViewModel(get()) }
    bean { RemoteRepository(get())as Repository } // bean = provide

}