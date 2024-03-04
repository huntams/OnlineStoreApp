package com.example.product

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
    dependencies = [ProductDeps::class],
    modules = [RepositoryModule::class, NetworkModule::class, DatabaseModule::class]
)]
internal interface ProductComponent {

    fun inject(fragment: ProductFragment)

    @Component.Builder
    interface Builder {

        fun deps(productDeps: ProductDeps): Builder

        fun build(): ProductComponent
    }
}

interface ProductDeps {
    val applicationContext: Context
}

interface ProductDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: ProductDeps

    companion object : ProductDepsProvider by ProductDepsStore
}

object ProductDepsStore : ProductDepsProvider {

    override var deps: ProductDeps by notNull()
}
internal class ProductComponentViewModel : ViewModel() {

    val productComponent =
        DaggerProductComponent.builder().deps(ProductDepsProvider.deps).build()
}