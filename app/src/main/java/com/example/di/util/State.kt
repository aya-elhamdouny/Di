package com.example.di.util


sealed class State<T>(
    val data : T? = null,
    val msg : String?= null
) {

    class Success<T>(data : T) : State<T>(data)
    class Error<T>( msg : String ,  data: T? = null) : State<T>( data , msg)
    class Loading<T> : State<T>()

}