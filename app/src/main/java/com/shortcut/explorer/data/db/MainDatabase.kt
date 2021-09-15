package com.shortcut.explorer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [],
    version = 0,
    exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class MainDatabase : RoomDatabase() {}