package com.shortcut.explorer.business.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shortcut.explorer.business.datasource.db.favorites.FavoriteDao

@Database(
    entities = [
        FavoriteDao::class
    ],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getFavoriteDao():FavoriteDao
}