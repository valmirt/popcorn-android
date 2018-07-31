package com.example.valmir.kotlinMvpDagger2.di.module

import com.example.valmir.kotlinMvpDagger2.ui.main.detail.DetailContract
import com.example.valmir.kotlinMvpDagger2.ui.main.detail.DetailPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideDetailPresenter(): DetailContract.Presenter = DetailPresenter()
}