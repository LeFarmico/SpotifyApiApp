package com.lefarmico.flatstacktesttask

import android.app.Application
import android.util.Log
import com.lefarmico.flatstacktesttask.di.AppComponent
import com.lefarmico.flatstacktesttask.di.DaggerAppComponent
import com.lefarmico.flatstacktesttask.di.modules.DataBaseModule
import com.lefarmico.flatstacktesttask.di.modules.DomainModule
import com.lefarmico.flatstacktesttask.di.modules.RemoteModule

class App : Application() {

    private val tag = this.javaClass.canonicalName

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        Log.d(tag, "on Create")

        appComponent = DaggerAppComponent.builder()
            .dataBaseModule(DataBaseModule(this))
            .domainModule(DomainModule())
            .remoteModule(RemoteModule())
            .build()
    }
}
