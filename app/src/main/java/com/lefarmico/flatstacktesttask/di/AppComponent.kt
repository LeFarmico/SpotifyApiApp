package com.lefarmico.flatstacktesttask.di

import com.lefarmico.flatstacktesttask.di.modules.DataBaseModule
import com.lefarmico.flatstacktesttask.di.modules.DomainModule
import com.lefarmico.flatstacktesttask.di.modules.RemoteModule
import com.lefarmico.flatstacktesttask.ui.main.MainViewModel
import com.lefarmico.flatstacktesttask.ui.splash.SplashViewModel
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RemoteModule::class,
        DataBaseModule::class,
        DomainModule::class
    ]
)

@Singleton
interface AppComponent {

    // ViewModels
    fun inject(mainViewModel: MainViewModel)
    fun inject(splashViewModel: SplashViewModel)
}
