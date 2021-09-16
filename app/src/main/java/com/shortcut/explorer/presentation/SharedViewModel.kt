package com.shortcut.explorer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.datasource.network.model.OnFail
import com.shortcut.explorer.business.datasource.network.search.toComic
import com.shortcut.explorer.business.domain.NetworkErrorCode
import com.shortcut.explorer.business.domain.model.Status
import com.shortcut.explorer.business.repositories.RecentComicsRepository
import com.shortcut.explorer.business.repositories.SearchComicsRepository
import com.shortcut.explorer.presentation._base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class SharedViewModel @Inject constructor(private val recentRepo: RecentComicsRepository, private val searchComicsRepository: SearchComicsRepository)
    : BaseViewModel() {

    private val _recentComics = MutableLiveData<List<Comic>>(null)
    val recentComics:LiveData<List<Comic>> = _recentComics

    private val _searchResult = MutableLiveData<List<Comic>>()
    val searchResult:LiveData<List<Comic>> = _searchResult

    suspend fun getRecentComics(onFail: OnFail){

        recentRepo.getRecentComics().collect {
            isLoading.value = it.status == Status.LOADING

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

    suspend fun searchComics(searchPhrase:String, onFail: OnFail){

        searchComicsRepository.search(searchPhrase).collect {
            isLoading.value = it.status == Status.LOADING

            when(it.status) {
                Status.SUCCESS -> {
                    isEmpty.value = it.data?.query?.search.isNullOrEmpty()
                    _searchResult.value = it.data?.query?.search
                        ?.map { it.toComic() }
                }
                Status.ERROR -> onFail(it.message?:"" , it.errorCode?: NetworkErrorCode.EXCEPTION)
                Status.LOADING -> Unit
            }

        }
    }



    fun loadMore() {

    }

    fun loadMoreSearchResults() {

    }

}
