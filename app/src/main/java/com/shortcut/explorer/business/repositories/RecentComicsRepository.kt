package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.datasource.network.main.ComicDto
import com.shortcut.explorer.business.datasource.network.main.MainApiService
import com.shortcut.explorer.business.datasource.network.main.toComic
import com.shortcut.explorer.business.datasource.network.model.OnSuccess
import com.shortcut.explorer.business.domain.Constants.RESENT_PAGE_ITEM_COUNT
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface RecentComicsRepository{

    suspend fun getRecentComics():Flow<Resource<List<Comic>>>
    suspend fun getLastComic(onSuccess: OnSuccess<ComicDto>):Resource<ComicDto>
    suspend fun getComicByNumber(number:Int, onSuccess: OnSuccess<ComicDto>):Resource<ComicDto>
}

class RecentComicsRepositoryImpl(
    private val mainApiService: MainApiService,
    private val networkWrapper: NetworkWrapper
):RecentComicsRepository {

    override suspend fun getRecentComics(): Flow<Resource<List<Comic>>> =flow {
        emit(Resource.loading(null))

        val comicsList = mutableListOf<Comic>()

        val lastComic = getLastComic { lastComic->

            repeat(RESENT_PAGE_ITEM_COUNT){
                getComicByNumber(lastComic.num - 1){
                        comicsList.add(it.toComic())
                }
            }

            emit(Resource.success(comicsList))
        }

        if (lastComic.status == Status.ERROR)
            emit(Resource(lastComic.status, comicsList, lastComic.message, lastComic.errorCode))
    }

    override suspend fun getLastComic(onSuccess: OnSuccess<ComicDto>): Resource<ComicDto> {

        val result = networkWrapper.fetch ({
            mainApiService.getLatestComic()
        }, onSuccess)

        return result
    }

    override suspend fun getComicByNumber(number: Int, onSuccess: OnSuccess<ComicDto>): Resource<ComicDto> {

        val result = networkWrapper.fetch ({
            mainApiService.getComicByNumber(num = number)
        },onSuccess)

        return result
    }


}










