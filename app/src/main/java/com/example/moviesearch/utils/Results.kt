package com.example.moviesearch.utils

class Results<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Results<T> {
            return Results(Status.SUCCESS, data, null)
        }

        fun <T> loading(message: String?): Results<T> {
            return Results(Status.LOADING, null, message)
        }

        fun <T> error(message: String?): Results<T> {
            return Results(Status.ERROR, null, message)
        }
    }
}

enum class Status{
    SUCCESS,
    LOADING,
    ERROR
}