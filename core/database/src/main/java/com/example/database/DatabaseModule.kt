package com.example.database

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {


    private const val DB_NAME = "DeliveryDatabase"

    @Provides
    @Singleton
    fun provideNotesDatabase(
        context: Context,
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
    fun provideResources(
        context: Context
    ): Resources {
        return context.resources
    }

    @Provides
    @Singleton
    fun provideStoreDAO(db: StoreDB): StoreDAO {
        return db.storeDAO()
    }
}