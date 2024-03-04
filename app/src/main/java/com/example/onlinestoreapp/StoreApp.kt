package com.example.onlinestoreapp

import android.app.Application
import android.content.Context
import com.example.catalog.CatalogDepsStore
import com.example.favourite.FavouriteDepsStore
import com.example.product.ProductDepsStore
import com.example.profile.ProfileDepsStore

class StoreApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        CatalogDepsStore.deps = appComponent
        ProfileDepsStore.deps = appComponent
        FavouriteDepsStore.deps = appComponent
        ProductDepsStore.deps = appComponent
    }


}
val Context.appComponent: AppComponent
    get() = when (this) {
        is StoreApp -> appComponent
        else -> applicationContext.appComponent
    }