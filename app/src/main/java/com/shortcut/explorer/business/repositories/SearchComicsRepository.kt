package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.datasource.network.main.ComicDto
import com.shortcut.explorer.business.datasource.network.main.MainApiService
import com.shortcut.explorer.business.datasource.network.main.toComic
import com.shortcut.explorer.business.datasource.network.model.OnSuccess
import com.shortcut.explorer.business.datasource.network.search.SearchApiService
import com.shortcut.explorer.business.datasource.network.search.SearchDto
import com.shortcut.explorer.business.domain.Constants.RESENT_PAGE_ITEM_COUNT
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchComicsRepository{
    suspend fun search(string:String):Flow<Resource<SearchDto>>
}

class SearchComicsRepositoryImpl(
    private val searchApiService: SearchApiService,
    private val networkWrapper: NetworkWrapper
):SearchComicsRepository {

    override suspend fun search(string: String): Flow<Resource<SearchDto>> = flow {
        emit(Resource.loading())

        val result = networkWrapper.fetch {
            searchApiService.search(string)
        }

        emit(result)
    }


}










