package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.datasource.network.main.Comic
import com.shortcut.explorer.business.datasource.network.main.ComicDto
import com.shortcut.explorer.business.datasource.network.main.MainApiService
import com.shortcut.explorer.business.datasource.network.main.toComic
import com.shortcut.explorer.business.datasource.network.model.OnSuccess
import com.shortcut.explorer.business.domain.Constants.RESENT_PAGE_ITEM_COUNT
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

interface RecentComicsRepository{

    suspend fun getRecentComics():Flow<Resource<List<Comic>>>
    suspend fun getLastComic():Resource<ComicDto>
    suspend fun getComicByNumber(number:Int, onSuccess: OnSuccess<ComicDto>):Resource<ComicDto>
}

class RecentComicsRepositoryImpl(
    private val mainApiService: MainApiService,
    private val networkWrapper: NetworkWrapper
):RecentComicsRepository {

    override suspend fun getRecentComics(): Flow<Resource<List<Comic>>> =flow {
        emit(Resource.loading(null))

        val comicsList = mutableListOf<Comic>()

        val lastComic = getLastComic()
        if (lastComic.status == Status.SUCCESS)

            repeat(RESENT_PAGE_ITEM_COUNT){
                getComicByNumber(lastComic.data!!.num - 1){
                    comicsList.add(it!!.toComic())
                }

            }

        else
            emit(Resource(lastComic.status, comicsList, lastComic.message, lastComic.errorCode))
    }

    override suspend fun getLastComic(): Resource<ComicDto> {

        val result = networkWrapper.fetch {
            mainApiService.getLatestComic()
        }

        return result
    }

    override suspend fun getComicByNumber(number: Int, onSuccess: OnSuccess<ComicDto>): Resource<ComicDto> {
        val result = networkWrapper.fetch ({
            mainApiService.getComicByNumber(num = number)
        },onSuccess)

        return result
    }


}










