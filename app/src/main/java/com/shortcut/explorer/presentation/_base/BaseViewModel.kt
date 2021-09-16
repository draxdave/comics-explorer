package com.shortcut.explorer.presentation._base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base [ViewModel] to hold basic and shared objects used by observers. For instance [isLoading] & [isEmpty].
 */
abstract class BaseViewModel :ViewModel() {
    val isLoading = MutableLiveData<Boolean>() /** When observed flow is in [com.shortcut.explorer.business.domain.model.Status.LOADING] state */
    val isEmpty = MutableLiveData<Boolean>() /** When observed value gets empty*/

    fun reset(){
        isLoading.value = false
        isEmpty.value = false
    }
}