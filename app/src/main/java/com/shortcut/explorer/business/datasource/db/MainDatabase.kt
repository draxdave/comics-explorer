package com.shortcut.explorer.business.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shortcut.explorer.business.datasource.db.favorites.FavoriteDao
import com.shortcut.explorer.business.datasource.db.favorites.FavoriteEntity

@Database(
    entities = [
        FavoriteEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getFavoriteDao():FavoriteDao
}