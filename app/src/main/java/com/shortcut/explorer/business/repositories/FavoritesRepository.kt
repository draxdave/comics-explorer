package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.db.favorites.FavoriteDao
import com.shortcut.explorer.business.datasource.db.favorites.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavoritesRepository {
    suspend fun getAllFavorites(): Flow<List<FavoriteEntity>>
    suspend fun getFavoriteByNumber(pageNumber:Int): Flow<FavoriteEntity>
    suspend fun deleteFavoriteByNumber(favoriteEntity:FavoriteEntity)
}

class FavoritesRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao
):FavoritesRepository{

    override suspend  fun getAllFavorites(): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorites()

    override suspend fun getFavoriteByNumber(pageNumber: Int): Flow<FavoriteEntity> = favoriteDao.getFavorite(pageNumber)

    override suspend fun deleteFavoriteByNumber(favoriteEntity: FavoriteEntity) = favoriteDao.deleteFavorite(favoriteEntity)

}