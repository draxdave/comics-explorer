package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.datasource.network.search.ExplainedDto
import com.shortcut.explorer.business.datasource.network.search.SearchApiService
import com.shortcut.explorer.business.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ExplainComicRepository {
    suspend fun getExplanationByPageId(pageId:Int): Flow<Resource<ExplainedDto>>
    suspend fun getExplanationByPage(page:String): Flow<Resource<ExplainedDto>>
}

class ExplainComicRepositoryImpl @Inject constructor(
    private val searchApiService: SearchApiService,
    private val networkWrapper: NetworkWrapper
):
    ExplainComicRepository {

    override suspend fun getExplanationByPageId(pageId: Int): Flow<Resource<ExplainedDto>> = flow {
        emit(Resource.loading())

        val result = networkWrapper.fetch {
            searchApiService.getExplanationByPageId(pageId)
        }

        emit(result)
    }

    override suspend fun getExplanationByPage(page : String): Flow<Resource<ExplainedDto>> = flow {
        emit(Resource.loading())

        val result = networkWrapper.fetch {
            searchApiService.getExplanationByPage(page)
        }

        emit(result)
    }

}