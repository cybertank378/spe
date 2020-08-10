package com.spe.spetest.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:42 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.entities.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
@Entity(tableName = "chars")
data class Chars(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)