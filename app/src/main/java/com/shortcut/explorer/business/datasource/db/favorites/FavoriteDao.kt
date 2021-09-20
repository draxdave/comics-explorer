package com.shortcut.explorer.business.datasource.db.favorites

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity): Long

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_comics WHERE pk=:pageNumber LIMIT 1")
    fun getFavorite(pageNumber: Int): Flow<FavoriteEntity?>

    @Query("SELECT * FROM favorite_comics")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>
}