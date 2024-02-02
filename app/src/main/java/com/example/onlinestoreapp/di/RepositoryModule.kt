package com.example.onlinestoreapp.di

import com.example.onlinestoreapp.data.db.repository.StoreDBRepository
import com.example.onlinestoreapp.data.db.repository.StoreDBRepositoryImpl
import com.example.onlinestoreapp.data.remote.repository.StoreRepository
import com.example.onlinestoreapp.data.remote.repository.StoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule() {

    @Binds
    abstract fun bindStoreDBRepository(impl: StoreDBRepositoryImpl): StoreDBRepository

    @Binds
    abstract fun bindStoreRepository(impl: StoreRepositoryImpl): StoreRepository
}