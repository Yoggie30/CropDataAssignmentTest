package `in`.cropdata.machine_test.yogesg_dhatrak.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    open fun errorHandling(errorCode: String): String {
        return when (errorCode) {
            "404" -> "Not found"
            "500" -> "Please try after sometime"
            else -> "Something went wrong, Please try after sometime"
        }
    }
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}