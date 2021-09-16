package com.shortcut.explorer.business.domain.model

/**#code from  : gitHubBrowserSample
 *
 * A generic class that holds a [data] value with its [loading] status or error [message] and
 * error code[errorCode].
 *
 * @param T : the data type to indicated the main data object with.
 * @property status : [Status] Latest status of the object
 * @property data : [T] data object.
 * @property message : [String] containing available success/fail message.
 * @property errorCode : [Int] possible unique error code to be passed to View in order to extract
 *      the more detailed message.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?, val errorCode : Int? = 0) {
    companion object {

        /**
         * Success resource object builder. Creates a [Resource] object with null [message] and
         * [Status.SUCCESS] status field.
         *
         * @param T : data type.
         * @param data : [T] content of type data.
         * @return :[Resource] a resource object with received params.
         */
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        /**
         * Error/Fail resource object builder. Creates a [Resource] object with [Status.ERROR]
         * status field. No params are mandatory here.
         *
         * @param T : data type.
         * @param data : [T] content of type data.
         * @param msg : [String] message of the error.
         * @param errorCode : [Int] error code to refer.
         * @return :[Resource] a resource object with received params.
         */
        fun <T> error(msg: String?=null, data: T? = null , errorCode: Int? = 0): Resource<T> {
            return Resource(Status.ERROR, data, msg ,errorCode)
        }

        /**
         * Loading resource object builder. Creates a [Resource] object with optional [data] param
         * and null [message].
         *
         * @param T : data type
         * @param data : [T] optional content.
         * @return : [Resource] a resource of loading status.
         */
        fun <T> loading(data: T?=null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
