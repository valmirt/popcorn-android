package com.example.valmir.kotlinMvpDagger2.di.module

import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomeContract
import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideHomePresenter(): HomeContract.Presenter = HomePresenter()
}