package com.example.onlinestoreapp

import android.content.Context
import com.example.catalog.CatalogDeps
import com.example.favourite.FavouriteDeps
import com.example.onlinestoreapp.presentation.MainActivity
import com.example.onlinestoreapp.presentation.StartFragment
import com.example.onlinestoreapp.presentation.discount.DiscountsFragment
import com.example.onlinestoreapp.presentation.home.HomeFragment
import com.example.onlinestoreapp.presentation.registration.RegistrationFragment
import com.example.onlinestoreapp.presentation.shop.ShopFragment
import com.example.product.ProductDeps
import com.example.profile.ProfileDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton


@Singleton
@[AppScope Component(modules = [AppModule::class])]
interface AppComponent: CatalogDeps,ProfileDeps,FavouriteDeps,ProductDeps {

    override val applicationContext: Context

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext( context: Context): Builder
        
        fun build(): AppComponent
    }

    fun inject(fragment: StartFragment)

    fun inject(activity: MainActivity)
    fun inject(fragment: DiscountsFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: ShopFragment)
}


@Scope
annotation class AppScope