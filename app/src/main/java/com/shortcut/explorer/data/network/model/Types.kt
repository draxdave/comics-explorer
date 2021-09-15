package com.shortcut.explorer.data.network.model

/**
 * Passes [ServerResponse] as parameter when the network request is successful.
 */
typealias  OnSuccess <T> = suspend (ServerResponse<T>?) -> Unit

/**
 * Passes error/failure details if the network request is failed.
 *
 * @param message : String message.
 * @param code : error code regarding the failure.
 */
typealias  OnFail = suspend (String,Int) -> Unit