package com.shortcut.explorer.business.domain.model

/**#code from  : gitHubBrowserSample
 * Status of a resource that is provided to the UI.
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` or Flow objects to pass back the latest data to the UI with its fetch
 * status.
 */
sealed class Status {
    /** Indication a Loading/Wait state */
    object SUCCESS : Status()
    object ERROR : Status()
    object LOADING : Status()
}

