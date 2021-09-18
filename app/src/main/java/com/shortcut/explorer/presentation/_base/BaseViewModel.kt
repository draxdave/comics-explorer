package com.shortcut.explorer.presentation._base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base [ViewModel] to hold basic and shared objects used by observers. For instance [isLoading] & [isEmpty].
 */
abstract class BaseViewModel :ViewModel() {

    fun setLoading(isLoading:Boolean){
        if (isLoading) activeLoading++
        else activeLoading--
    }

    private var activeLoading:Int = 0
        set(value) {
            field=value
            _isLoading.value = field != 0
        }

    private val _isLoading = MutableLiveData<Boolean>() /** When observed flow is in [com.shortcut.explorer.business.domain.model.Status.LOADING] state */
    val isLoading:LiveData<Boolean> = _isLoading
    val isEmpty = MutableLiveData<Boolean>() /** When observed value gets empty*/

    fun reset(){
        activeLoading = 0
        isEmpty.value = false
    }
}