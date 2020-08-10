package com.spe.spetest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spe.spetest.data.entities.Chars

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:58 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.local.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
@Dao
interface CharsDao {

    @Query("SELECT * FROM chars")
    fun getAllCharacters() : LiveData<List<Chars>>

    @Query("SELECT * FROM chars WHERE id = :id")
    fun getCharacter(id: Int): LiveData<Chars>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Chars>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Chars)


}