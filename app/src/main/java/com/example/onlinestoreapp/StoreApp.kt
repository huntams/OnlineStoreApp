package com.example.onlinestoreapp

import android.app.Application
import android.content.Context

class StoreApp : Application() {

    lateinit var appComponent: AppComponent
        private set
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().applicationContext(this).build()
    }


}
val Context.appComponent: AppComponent
    get() = when (this) {
        is StoreApp -> appComponent
        else -> applicationContext.appComponent
    }