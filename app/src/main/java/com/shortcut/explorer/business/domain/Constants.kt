package com.shortcut.explorer.business.domain

import com.shortcut.explorer.R

object Constants {

    const val RESENT_PAGE_ITEM_COUNT = 10

    const val SERIALIZABLE_COMIC_OBJECT_NAME = "serialized_comic"

    fun errorCodeToString(code:Int?): Int =
        when(code){
            java.net.HttpURLConnection.HTTP_NOT_FOUND -> R.string.error_not_found
            NetworkErrorCode.CONNECT_EXCEPTION            -> R.string.network_unavailable
            NetworkErrorCode.SOCKET_TIME_OUT_EXCEPTION      -> R.string.network_unavailable
            NetworkErrorCode.UNKNOWN_HOST_EXCEPTION        -> R.string.network_unavailable
            in java.net.HttpURLConnection.HTTP_INTERNAL_ERROR..java.net.HttpURLConnection.HTTP_VERSION -> R.string.error_internal
            in java.net.HttpURLConnection.HTTP_UNAUTHORIZED..java.net.HttpURLConnection.HTTP_FORBIDDEN ->{
                R.string.error_unauthorized
            }
            else -> R.string.unknown_error
        }

}