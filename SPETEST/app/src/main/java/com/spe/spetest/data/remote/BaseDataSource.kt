package com.spe.spetest.data.remote

import com.spe.spetest.utils.Resource
import retrofit2.Response
import timber.log.Timber
/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:46 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.remote.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}