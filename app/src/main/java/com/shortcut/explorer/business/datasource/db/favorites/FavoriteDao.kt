package com.shortcut.explorer.business.datasource.db.favorites

import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity): Long

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_comics WHERE pk=:pageNumber")
    suspend fun getAllBlogPosts(pageNumber: Int): List<FavoriteEntity>
}