package com.example.onlinestoreapp

import android.content.Context
import com.example.onlinestoreapp.presentation.MainActivity
import com.example.onlinestoreapp.presentation.StartFragment
import com.example.onlinestoreapp.presentation.catalog.CatalogFragment
import com.example.onlinestoreapp.presentation.catalog.ProductFragment
import com.example.onlinestoreapp.presentation.discount.DiscountsFragment
import com.example.onlinestoreapp.presentation.home.HomeFragment
import com.example.onlinestoreapp.presentation.profile.FavouriteFragment
import com.example.onlinestoreapp.presentation.profile.ProfileFragment
import com.example.onlinestoreapp.presentation.registration.RegistrationFragment
import com.example.onlinestoreapp.presentation.shop.ShopFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder
        
        fun build(): AppComponent
    }

    fun inject(fragment: StartFragment)

    fun inject(activity: MainActivity)
    fun inject(fragment: CatalogFragment)
    fun inject(fragment: ProductFragment)
    fun inject(fragment: DiscountsFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavouriteFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: ShopFragment)
}
