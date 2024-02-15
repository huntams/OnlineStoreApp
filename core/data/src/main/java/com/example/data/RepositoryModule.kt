package com.example.data

import com.example.data.repository.StoreDBRepository
import com.example.data.repository.StoreDBRepositoryImpl
import com.example.data.repository.StoreRepository
import com.example.data.repository.StoreRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindStoreDBRepository(impl: StoreDBRepositoryImpl): StoreDBRepository

    @Binds
    abstract fun bindStoreRepository(impl: StoreRepositoryImpl): StoreRepository
}