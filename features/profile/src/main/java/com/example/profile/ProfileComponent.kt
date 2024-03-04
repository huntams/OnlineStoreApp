package com.example.profile

import android.content.Context
import androidx.annotation.RestrictTo
import androidx.lifecycle.ViewModel
import com.example.data.di.Feature
import com.example.data.di.RepositoryModule
import com.example.database.DatabaseModule
import com.example.network.remote.NetworkModule
import dagger.Component
import javax.inject.Singleton
import kotlin.properties.Delegates

@Singleton
@[Feature Component(
    dependencies = [ProfileDeps::class],
    modules = [RepositoryModule::class, NetworkModule::class, DatabaseModule::class]
)]
internal interface ProfileComponent {

    fun inject(fragment: ProfileFragment)

    @Component.Builder
    interface Builder {

        fun deps(profileDeps: ProfileDeps): Builder

        fun build(): ProfileComponent
    }
}

interface ProfileDeps {
    val applicationContext: Context
}

interface ProfileDepsProvider {

    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    val deps: ProfileDeps

    companion object : ProfileDepsProvider by ProfileDepsStore
}

object ProfileDepsStore : ProfileDepsProvider {

    override var deps: ProfileDeps by Delegates.notNull()
}
internal class ProfileComponentViewModel : ViewModel() {

    val profileComponent = DaggerProfileComponent.builder().deps(ProfileDepsProvider.deps).build()
}