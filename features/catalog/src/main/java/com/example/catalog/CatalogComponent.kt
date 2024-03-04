package com.example.catalog

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
    dependencies = [CatalogDeps::class],
    modules = [RepositoryModule::class, NetworkModule::class, DatabaseModule::class]
)]
internal interface CatalogComponent {

    fun inject(fragment: CatalogFragment)

    @Component.Builder
    interface Builder {

        fun deps(catalogDeps: CatalogDeps): Builder

        fun build(): CatalogComponent
    }
}

interface CatalogDeps {
    val applicationContext: Context
}

interface CatalogDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: CatalogDeps

    companion object : CatalogDepsProvider by CatalogDepsStore
}

object CatalogDepsStore : CatalogDepsProvider {

    override var deps: CatalogDeps by notNull()
}
internal class CatalogComponentViewModel : ViewModel() {

    val catalogComponent =
        DaggerCatalogComponent.builder().deps(CatalogDepsProvider.deps).build()
}