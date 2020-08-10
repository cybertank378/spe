package com.spe.spetest.data.remote

import javax.inject.Inject

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:53 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.remote.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */

class CharsRemoteDataSource @Inject constructor(
    private val charsService: CharsService
): BaseDataSource() {

    suspend fun getCharacters() = getResult { charsService.getAllCharacters() }
    suspend fun getCharacter(id: Int) = getResult { charsService.getCharacter(id) }
}