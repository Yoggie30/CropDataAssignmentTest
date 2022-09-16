package `in`.cropdata.machine_test.yogesg_dhatrak.base

abstract class BaseRepository {

    open fun errorHandling(errorCode: String): String {
        return when (errorCode) {
            "404" -> "Not found"
            "500" -> "Please try after sometime"
            else -> "Something went wrong, Please try after sometime"
        }
    }


}
