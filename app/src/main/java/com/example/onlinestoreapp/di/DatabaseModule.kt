package com.example.onlinestoreapp.di

import android.content.Context
import androidx.room.Room
import com.example.onlinestoreapp.data.db.StoreDAO
import com.example.onlinestoreapp.data.db.StoreDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    private const val DB_NAME = "DeliveryDatabase"

    @Provides
    @Singleton
    fun provideNotesDatabase(
        @ApplicationContext context: Context,
    ): StoreDB {
        return Room.databaseBuilder(
            context,
            StoreDB::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStoreDAO(db: StoreDB): StoreDAO {
        return db.storeDAO()
    }
}