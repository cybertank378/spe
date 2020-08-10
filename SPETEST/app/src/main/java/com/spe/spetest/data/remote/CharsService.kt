package com.spe.spetest.data.remote

import com.spe.spetest.data.entities.Chars
import com.spe.spetest.data.entities.CharsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:54 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.remote.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
interface CharsService {
    @GET("character")
    suspend fun getAllCharacters() : Response<CharsList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Chars>
}