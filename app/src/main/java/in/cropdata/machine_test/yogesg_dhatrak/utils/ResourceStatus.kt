package `in`.cropdata.machine_test.yogesg_dhatrak.utils

sealed class ResourceStatus<T>{
    class Loading<T>():ResourceStatus<T>()
    class Success<T>(val data:T?):ResourceStatus<T>()
    class Error<T>(val message:String,val data:T?=null):ResourceStatus<T>()
}