package com.shortcut.explorer.business.domain

object NetworkErrorCode{
    const val CONNECT_EXCEPTION = 600           /** It means there is no internet connection around. */
    const val SOCKET_TIME_OUT_EXCEPTION = 601   /** No internet connection or poor connection quality */
    const val UNKNOWN_HOST_EXCEPTION = 602      /** Probably a dns issue or wrong host address */
    const val EXCEPTION = 603                   /** Something that should be investigated */
}