package com.example.myapplication3.data.remote

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()

    data class Success<T>(val data : T) :Resource<T>()

    data class Error(val message : String,
        val exception: Throwable? = null, ) : Resource<Nothing>()
}