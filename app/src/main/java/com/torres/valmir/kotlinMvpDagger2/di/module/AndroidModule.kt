package com.torres.valmir.kotlinMvpDagger2.di.module

import android.app.Application
import android.content.Context
import com.torres.valmir.kotlinMvpDagger2.di.ForApplication
import com.torres.valmir.kotlinMvpDagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlinMvpDagger2.remote.movie.MovieServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {
    @Provides
    @Singleton
    @ForApplication
    fun provideApplication(): Context = application

    @Provides
    @Singleton
    fun provideAppContext(): Context = application.applicationContext

    @Provides
    fun provideServiceImpl(): MovieServiceApi = MovieServiceImpl()
}