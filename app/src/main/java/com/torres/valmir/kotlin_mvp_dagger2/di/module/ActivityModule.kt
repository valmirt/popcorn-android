package com.torres.valmir.kotlin_mvp_dagger2.di.module

import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomePresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver.ReceiverContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver.ReceiverPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings.SettingsContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings.SettingsPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash.SplashContract
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun provideHomePresenter(): HomeContract.Presenter = HomePresenter()

    @Provides
    fun provideAboutPresenter(): AboutContract.Presenter = AboutPresenter()

    @Provides
    fun provideSettingsPresenter(): SettingsContract.Presenter = SettingsPresenter()

    @Provides
    fun provideDetailPresenter(): DetailContract.Presenter = DetailPresenter()

    @Provides
    fun provideSplashPresenter(): SplashContract.Presenter = SplashPresenter()

    @Provides
    fun provideReceiverPresenter(): ReceiverContract.Presenter = ReceiverPresenter()
}