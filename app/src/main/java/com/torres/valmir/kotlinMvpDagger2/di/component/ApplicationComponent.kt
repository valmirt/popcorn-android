package com.torres.valmir.kotlinMvpDagger2.di.component

import android.app.Application
import com.torres.valmir.kotlinMvpDagger2.di.module.ActivityModule
import com.torres.valmir.kotlinMvpDagger2.di.module.AndroidModule
import com.torres.valmir.kotlinMvpDagger2.di.module.FragmentModule
import com.torres.valmir.kotlinMvpDagger2.di.module.NetModule
import com.torres.valmir.kotlinMvpDagger2.remote.ServiceImpl
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.casting.CastingFragment
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.casting.CastingPresenter
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.info.InfoFragment
import com.torres.valmir.kotlinMvpDagger2.ui.main.detail.info.InfoPresenter
import com.torres.valmir.kotlinMvpDagger2.ui.main.home.HomePresenter
import com.torres.valmir.kotlinMvpDagger2.ui.main.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (AndroidModule::class), ActivityModule::class, FragmentModule::class])
interface ApplicationComponent {

    fun inject(application: Application)

    fun inject(serviceImpl: ServiceImpl)

    fun inject(homePresenter: HomePresenter)

    fun inject(homeFragment: HomeFragment)

    fun inject(infoPresenter: InfoPresenter)

    fun inject(infoFragment: InfoFragment)

    fun inject(castingFragment: CastingFragment)

    fun inject(castingPresenter: CastingPresenter)
}