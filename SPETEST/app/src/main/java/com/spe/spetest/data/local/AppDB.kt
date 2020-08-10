package com.spe.spetest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spe.spetest.data.entities.Chars

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:56 PM.
 * ===================================================
 * Package              : com.spe.spetest.data.local.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */
@Database(entities = [Chars::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {

    abstract fun characterDao(): CharsDao

    companion object {
        @Volatile private var instance: AppDb? = null

        fun getDatabase(context: Context): AppDb =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDb::class.java, "chars")
                .fallbackToDestructiveMigration()
                .build()
    }

}