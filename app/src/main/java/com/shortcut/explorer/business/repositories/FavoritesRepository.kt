package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.db.favorites.FavoriteDao
import com.shortcut.explorer.business.datasource.db.favorites.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoritesRepository {
    fun getAllFavorites(): Flow<List<FavoriteEntity>>
    fun getFavoriteByNumber(pageNumber:Int): Flow<FavoriteEntity?>
    suspend fun deleteFavorite(favoriteEntity:FavoriteEntity)
    suspend fun addFavorite(favoriteEntity:FavoriteEntity):Long
}

class FavoritesRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
):FavoritesRepository{

    override fun getAllFavorites(): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    override fun getFavoriteByNumber(pageNumber: Int): Flow<FavoriteEntity?> = favoriteDao.getFavorite(pageNumber)

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.deleteFavorite(favoriteEntity)

    override suspend fun addFavorite(favoriteEntity: FavoriteEntity) = favoriteDao.insert(favoriteEntity)

}