package com.spe.spetest.data.repo

import com.spe.spetest.data.local.CharsDao
import com.spe.spetest.data.remote.CharsRemoteDataSource
import com.spe.spetest.utils.performGetOperation
import javax.inject.Inject

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:44 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.repo.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
class CharsRepo@Inject constructor(
    private val remoteDataSource: CharsRemoteDataSource,
    private val localDataSource: CharsDao
) {

    fun getChar(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getCharacter(id) },
        networkCall = { remoteDataSource.getCharacter(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getChars() = performGetOperation(
        databaseQuery = { localDataSource.getAllCharacters() },
        networkCall = { remoteDataSource.getCharacters() },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )
}