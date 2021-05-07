package com.lefarmico.flatstacktesttask.di.modules

import com.lefarmico.flatstacktesttask.data.Interactor
import com.lefarmico.flatstacktesttask.data.SpotifyApi
import com.lefarmico.flatstacktesttask.db.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideInteractor(repository: MainRepository, spotifyApi: SpotifyApi) = Interactor(repository, spotifyApi)
}