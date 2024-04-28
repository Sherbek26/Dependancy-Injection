package com.example.dependancyinjection.utility

data class NetworkResult<out T> (
    val status: NetWorkStatus,
    val data : T?,
    val message : String?
    ){
    companion object{
        fun <T> success(data : T) : NetworkResult<T>{
            return NetworkResult(NetWorkStatus.SUCCESS,data,null)
        }

        fun <T> error(message: String) : NetworkResult<T> {
            return NetworkResult(NetWorkStatus.ERROR,null,message)
        }

        fun <T> loading() : NetworkResult<T> {
            return NetworkResult(NetWorkStatus.LOADING,null,null)
        }
    }
}