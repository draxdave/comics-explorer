package com.shortcut.explorer.di.module.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shortcut.explorer.business.datasource.db.MainDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(appContext: Context):  MainDatabase {
        return Room.databaseBuilder(appContext, MainDatabase::class.java, "Comics_db")
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


}