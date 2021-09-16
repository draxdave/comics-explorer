package com.shortcut.explorer.presentation.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shortcut.explorer.business.datasource.network.main.Comic
import com.shortcut.explorer.business.repositories.RecentComicsRepository
import com.shortcut.explorer.presentation._base.BaseViewModel
import javax.inject.Inject

class RecentViewModel @Inject constructor(private val recentRepo: RecentComicsRepository): BaseViewModel() {

    private val _recentComics = MutableLiveData<Comic>()
    val recentComics:LiveData<Comic> = _recentComics

    fun getRecentComics(){

    }

}
