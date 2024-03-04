package com.example.onlinestoreapp

import com.example.data.di.RepositoryModule
import com.example.database.DatabaseModule
import com.example.network.remote.NetworkModule
import dagger.Module

@Module(includes = [NetworkModule::class, RepositoryModule::class,DatabaseModule::class])
class AppModule