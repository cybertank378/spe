package com.spe.spetest.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import com.spe.spetest.utils.Resource.Status.SUCCESS
import com.spe.spetest.utils.Resource.Status.ERROR

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:50 PM.
 * ===================================================
 * Package              : com.spe.spetest.utils.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }