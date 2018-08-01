package com.example.valmir.kotlinMvpDagger2.di.module

import com.example.valmir.kotlinMvpDagger2.ui.main.detail.casting.CastingContract
import com.example.valmir.kotlinMvpDagger2.ui.main.detail.casting.CastingPresenter
import com.example.valmir.kotlinMvpDagger2.ui.main.detail.info.InfoContract
import com.example.valmir.kotlinMvpDagger2.ui.main.detail.info.InfoPresenter
import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomeContract
import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideHomePresenter(): HomeContract.Presenter = HomePresenter()

    @Provides
    fun provideInfoPresenter(): InfoContract.Presenter = InfoPresenter()

    @Provides
    fun provideCastingPresenter(): CastingContract.Presenter = CastingPresenter()
}