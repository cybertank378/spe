package com.spe.spetest.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.spe.spetest.BuildConfig.BASE_URL
import com.spe.spetest.data.local.AppDb
import com.spe.spetest.data.local.CharsDao
import com.spe.spetest.data.remote.CharsRemoteDataSource
import com.spe.spetest.data.remote.CharsService
import com.spe.spetest.data.repo.CharsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created              : Rahman on 10/08/2020.
 * Date Created         : 10/08/2020 / 1:22 PM.
 * ===================================================
 * Package              : com.spe.spetest.di.
 * Project Name         : SPE TEST.
 * Copyright            : Copyright @ 2020.
 */

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharsService = retrofit.create(CharsService::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharsService) = CharsRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDb.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDb) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharsRemoteDataSource,
                          localDataSource: CharsDao
    ) =
        CharsRepo(remoteDataSource, localDataSource)
}