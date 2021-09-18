package com.shortcut.explorer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shortcut.explorer.business.datasource.network.main.ComicDto
import com.shortcut.explorer.business.datasource.network.model.OnFail
import com.shortcut.explorer.business.datasource.network.search.toQuery
import com.shortcut.explorer.business.domain.NetworkErrorCode
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.SearchResult
import com.shortcut.explorer.business.domain.model.Status
import com.shortcut.explorer.business.repositories.ExplainComicRepository
import com.shortcut.explorer.business.repositories.RecentComicsRepository
import com.shortcut.explorer.business.repositories.SearchComicsRepository
import com.shortcut.explorer.presentation._base.BaseViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SharedViewModel @Inject constructor(
    private val recentRepo: RecentComicsRepository,
    private val explainComicRepository: ExplainComicRepository,
    private val searchComicsRepository: SearchComicsRepository
)
    : BaseViewModel() {

    //=============== Recent Comics

    private val _recentComics = MutableLiveData<List<Comic>>(null)
    val recentComics:LiveData<List<Comic>> = _recentComics

    suspend fun getRecentComics(onFail: OnFail){

        recentRepo.getRecentComics().collect {
            setLoading(it.status == Status.LOADING)

            when(it.status) {
                Status.SUCCESS -> {
                    _recentComics.value = it.data
                    isEmpty.value = it.data.isNullOrEmpty()
                }
                Status.ERROR -> onFail(it.message?:"" , it.errorCode?: NetworkErrorCode.EXCEPTION)
                Status.LOADING -> Unit
            }

        }
    }

    fun loadMore() {

    }

    //End of =============== Recent Comics

    //=============== Search Comics

    private val _searchResult = MutableLiveData<List<SearchResult>>()
    val searchResult:LiveData<List<SearchResult>> = _searchResult

    suspend fun searchComics(searchPhrase:String, onFail: OnFail){

        searchComicsRepository.search(searchPhrase).collect {
            setLoading(it.status == Status.LOADING)

            when(it.status) {
                Status.SUCCESS -> {
                    isEmpty.value = it.data?.query?.search.isNullOrEmpty()
                    _searchResult.value = it.data?.query?.search
                        ?.mapNotNull { it.toQuery() }
                }
                Status.ERROR -> onFail(it.message?:"" , it.errorCode?: NetworkErrorCode.EXCEPTION)
                Status.LOADING -> Unit
            }
        }
    }

    fun loadMoreSearchResults() {

    }

    //End of =============== Search Comics

    //=============== Comic Details

    suspend fun retrieveComicExplanationByPageId(pageId:Int) = explainComicRepository.getExplanationByPageId(pageId).onEach {
        setLoading(it.status == Status.LOADING)
    }

    suspend fun retrieveComicExplanationByPage(page:String) = explainComicRepository.getExplanationByPage(page).onEach {
        setLoading(it.status == Status.LOADING)
    }

    suspend fun retrieveComic(pageNumber:Int):Flow<Resource<ComicDto>> = flow {
        setLoading(true)
        emit(
            recentRepo.getComicByNumber(pageNumber)
        )
    }
}
