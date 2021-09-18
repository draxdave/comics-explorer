package com.shortcut.explorer.presentation.util


import com.shortcut.explorer.R
import com.shortcut.explorer.business.domain.NetworkErrorCode
import com.shortcut.explorer.business.domain.model.OnChange
import com.shortcut.explorer.business.domain.model.Resource
import com.shortcut.explorer.business.domain.model.Status
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import java.net.HttpURLConnection

/**
 * Flow ResourceObserver : This is a medium to parse and handle network requests using Resource pattern.
 *
 * @param message: ((Int, String?)-> Unit) -> Method passing transaction message to a higher level.
 * @param onChange :Observer.OnChange -> The callback function to .
 *
 */

class FlowObserver<E>(private val message: ((Int, String?)-> Unit)={ _, _->}
                      , val onChange: OnChange<E> = {})
    : FlowCollector<Resource<E>> {

    /**
     * A new value is emitted in the stream.
     * First, message is extracted if there is any.
     * then, State is checked and reacts appropriately.
     * At the end deliver the value to observer.
     *
     * @param value : [E] the new value.
     */
    override suspend fun emit(value: Resource<E>) {
        value.let {
            it.message.takeIf { message -> !message.isNullOrEmpty() }?.let { msg ->
                message(0,msg)
            }

            if(it.status == Status.ERROR) {

                    val message = when(it.errorCode){
                        HttpURLConnection.HTTP_NOT_FOUND -> R.string.error_not_found
                        NetworkErrorCode.CONNECT_EXCEPTION            -> R.string.network_unavailable
                        NetworkErrorCode.SOCKET_TIME_OUT_EXCEPTION      -> R.string.network_unavailable
                        NetworkErrorCode.UNKNOWN_HOST_EXCEPTION        -> R.string.network_unavailable
                        in HttpURLConnection.HTTP_INTERNAL_ERROR..HttpURLConnection.HTTP_VERSION -> R.string.error_internal

                        in HttpURLConnection.HTTP_UNAUTHORIZED..HttpURLConnection.HTTP_FORBIDDEN ->{
                            R.string.error_unauthorized
                        }
                        else -> R.string.unknown_error
                    }

                    // Display local message text if api response has not provided one.
                    if (it.message.isNullOrEmpty())
                        message(message,null)
                }
        }
        onChange(value)
    }
}

/**
 * Extension function to make it easy to observe flows.
 *
 * @param T: data type.
 * @param message : Message bus.
 * @param onChange : [OnChange] tracking lambda.
 */
@InternalCoroutinesApi
suspend fun <T> Flow<Resource<T>>.observe(
    message: ((Int, String?)-> Unit)={ _, _->},
    onChange: OnChange<T> = {}
){
    collect(
        FlowObserver(
            message = message,
            onChange = onChange
        )
    )
}