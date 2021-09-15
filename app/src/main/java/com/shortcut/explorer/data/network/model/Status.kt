package com.shortcut.explorer.data.network.model

/**#code from  : gitHubBrowserSample
 * Status of a resource that is provided to the UI.
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Resource<T>>` or Flow objects to pass back the latest data to the UI with its fetch
 * status.
 */
enum class Status {
    SUCCESS, /** Indication a Success state */
    ERROR, /** Indication a Failure/Cancel/Error/non-configured state */
    LOADING /** Indication a Loading/Wait state */
}

