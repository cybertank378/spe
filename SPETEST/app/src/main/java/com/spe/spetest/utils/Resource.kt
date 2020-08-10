package com.spe.spetest.utils

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:48 PM.
 * ===================================================
 * Package              : com.spe.spetest.utils.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
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