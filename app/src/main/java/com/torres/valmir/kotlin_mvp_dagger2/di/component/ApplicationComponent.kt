package com.torres.valmir.kotlin_mvp_dagger2.di.component

import android.app.Application
import com.torres.valmir.kotlin_mvp_dagger2.di.module.ActivityModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.AndroidModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.FragmentModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.NetModule
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceImpl
import com.torres.valmir.kotlin_mvp_dagger2.remote.tvShow.TvServiceImpl
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season.SeasonFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings.SettingsActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (AndroidModule::class), ActivityModule::class, FragmentModule::class])
interface ApplicationComponent {

    fun inject(application: Application)

    fun inject(movieServiceImpl: MovieServiceImpl)

    fun inject(listPresenter: ListPresenter)

    fun inject(listFragment: ListFragment)

    fun inject(infoPresenter: InfoPresenter)

    fun inject(infoFragment: InfoFragment)

    fun inject(castingFragment: CastingFragment)

    fun inject(castingPresenter: CastingPresenter)

    fun inject(seasonFragment: SeasonFragment)

    fun inject(tvServiceImpl: TvServiceImpl)

    fun inject(aboutActivity: AboutActivity)

    fun inject (detailActivity: DetailActivity)

    fun inject (homeActivity: HomeActivity)

    fun inject (settingsActivity: SettingsActivity)

    fun inject (splashActivity: SplashActivity)
}