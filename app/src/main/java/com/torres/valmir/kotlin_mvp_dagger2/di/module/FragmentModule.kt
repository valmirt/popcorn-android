package com.torres.valmir.kotlin_mvp_dagger2.di.module

import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season.SeasonContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season.SeasonPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideListPresenter(): ListContract.Presenter = ListPresenter()

    @Provides
    fun provideInfoPresenter(): InfoContract.Presenter = InfoPresenter()

    @Provides
    fun provideCastingPresenter(): CastingContract.Presenter = CastingPresenter()

    @Provides
    fun provideSeasonPresenter(): SeasonContract.Presenter = SeasonPresenter()
}