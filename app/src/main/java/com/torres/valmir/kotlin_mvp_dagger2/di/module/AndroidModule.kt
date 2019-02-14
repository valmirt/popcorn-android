package com.torres.valmir.kotlin_mvp_dagger2.di.module

import android.app.Application
import android.content.Context
import com.torres.valmir.kotlin_mvp_dagger2.di.ForApplication
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceImpl
import com.torres.valmir.kotlin_mvp_dagger2.remote.person.PersonApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.person.PersonImpl
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceImpl
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
    fun provideMovieServiceImpl(): MovieServiceApi = MovieServiceImpl()

    @Provides
    fun provideTvServiceImpl(): TvServiceApi = TvServiceImpl()

    @Provides
    fun providePersonImpl(): PersonApi = PersonImpl()
}