package com.shortcut.explorer.presentation.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shortcut.explorer.business.domain.model.Comic
import com.shortcut.explorer.business.datasource.network.model.OnFail
import com.shortcut.explorer.business.domain.NetworkErrorCode
import com.shortcut.explorer.business.domain.model.Status
import com.shortcut.explorer.business.repositories.RecentComicsRepository
import com.shortcut.explorer.presentation._base.BaseViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class RecentViewModel @Inject constructor(private val recentRepo: RecentComicsRepository): BaseViewModel() {

    private val _recentComics = MutableLiveData<List<Comic>>()
    val recentComics:LiveData<List<Comic>> = _recentComics

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

}
