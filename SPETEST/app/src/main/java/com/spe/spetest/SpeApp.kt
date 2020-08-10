package com.spe.spetest

import android.app.Application
import androidx.viewbinding.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:13 PM.
 * ===================================================
 * Package              : com.spe.spetest.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */

@HiltAndroidApp
class SpeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}