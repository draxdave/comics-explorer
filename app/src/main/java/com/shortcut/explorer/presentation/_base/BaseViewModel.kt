package com.shortcut.explorer.presentation._base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Base [ViewModel] to hold basic and shared objects used by observers. For instance [isLoading] & [isEmpty].
 */
abstract class BaseViewModel :ViewModel() {
    internal val isLoading = MutableLiveData(false) /** When observed flow is in [com.shortcut.explorer.business.domain.model.Status.LOADING] state */
    internal val isEmpty = MutableLiveData(true) /** When observed value gets empty*/
}