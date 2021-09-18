package com.shortcut.explorer.business.repositories

import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.datasource.network.search.ExplainedDto
import com.shortcut.explorer.business.datasource.network.search.SearchApiService
import com.shortcut.explorer.business.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ExplainComicRepository {
    suspend fun getExplanation(pageId:Int): Flow<Resource<ExplainedDto>>
}

class ExplainComicRepositoryImpl @Inject constructor(
    private val searchApiService: SearchApiService,
    private val networkWrapper: NetworkWrapper
):
    ExplainComicRepository {

    override suspend fun getExplanation(pageId: Int): Flow<Resource<ExplainedDto>> = flow {
        emit(Resource.loading())

        val result = networkWrapper.fetch {
            searchApiService.getExplanation(pageId)
        }

        emit(result)
    }

}