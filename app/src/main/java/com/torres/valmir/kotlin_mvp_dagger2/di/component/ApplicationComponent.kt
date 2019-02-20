package com.torres.valmir.kotlin_mvp_dagger2.di.component

import android.app.Application
import com.torres.valmir.kotlin_mvp_dagger2.di.module.ActivityModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.AndroidModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.FragmentModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.NetModule
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceImpl
import com.torres.valmir.kotlin_mvp_dagger2.remote.person.PersonServiceImpl
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceImpl
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season.SeasonFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.DetailPersonActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.DetailPersonPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomePresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.biography.InfoPersonFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.biography.InfoPersonPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.credits.CreditsFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.credits.CreditsPresenter
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver.ReceiverActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver.ReceiverPresenter
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

    fun inject (aboutPresenter: AboutPresenter)

    fun inject (detailActivity: DetailActivity)

    fun inject (homeActivity: HomeActivity)

    fun inject (homePresenter: HomePresenter)

    fun inject (settingsActivity: SettingsActivity)

    fun inject (splashActivity: SplashActivity)

    fun inject (detailPresenter: DetailPresenter)

    fun inject (receiverActivity: ReceiverActivity)

    fun inject (receiverPresenter: ReceiverPresenter)

    fun inject (personImpl: PersonServiceImpl)

    fun inject (detailPersonActivity: DetailPersonActivity)

    fun inject (detailPersonPresenter: DetailPersonPresenter)

    fun inject (infoPersonFragment: InfoPersonFragment)

    fun inject (infoPersonPresenter: InfoPersonPresenter)

    fun inject (creditsFragment: CreditsFragment)

    fun inject (creditsPresenter: CreditsPresenter)
}