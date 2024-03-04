package com.example.favourite

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.data.di.Feature
import com.example.data.di.RepositoryModule
import com.example.database.DatabaseModule
import com.example.network.remote.NetworkModule
import dagger.Component
import javax.inject.Singleton
import kotlin.properties.Delegates.notNull


@Singleton
@[Feature Component(
    dependencies = [FavouriteDeps::class],
    modules = [RepositoryModule::class, NetworkModule::class, DatabaseModule::class]
)]
internal interface FavouriteComponent {

    fun inject(fragment: FavouriteFragment)

    @Component.Builder
    interface Builder {

        fun deps(catalogDeps: FavouriteDeps): Builder

        fun build(): FavouriteComponent
    }
}

interface FavouriteDeps {
    val applicationContext: Context
}

interface FavouriteDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: FavouriteDeps

    companion object : FavouriteDepsProvider by FavouriteDepsStore
}

object FavouriteDepsStore : FavouriteDepsProvider {

    override var deps: FavouriteDeps by notNull()
}

internal class CatalogComponentViewModel : ViewModel() {

    val favouriteComponent =
        DaggerFavouriteComponent.builder().deps(FavouriteDepsProvider.deps).build()
}