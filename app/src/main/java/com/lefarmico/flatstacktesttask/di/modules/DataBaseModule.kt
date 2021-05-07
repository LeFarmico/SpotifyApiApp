package com.lefarmico.flatstacktesttask.di.modules

import android.content.Context
import androidx.room.Room
import com.lefarmico.flatstacktesttask.db.AppDatabase
import com.lefarmico.flatstacktesttask.db.MainRepository
import com.lefarmico.flatstacktesttask.db.MusicDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideRepository(musicDao: MusicDao): MainRepository = MainRepository(musicDao)

    @Provides
    @Singleton
    fun provideMusicDao(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "cached_music_items"
    ).build().musicDao()
}
