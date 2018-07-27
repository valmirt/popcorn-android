package com.example.valmir.kotlinMvpDagger2.di.component

import android.app.Application
import com.example.valmir.kotlinMvpDagger2.di.module.ActivityModule
import com.example.valmir.kotlinMvpDagger2.di.module.AndroidModule
import com.example.valmir.kotlinMvpDagger2.di.module.FragmentModule
import com.example.valmir.kotlinMvpDagger2.di.module.NetModule
import com.example.valmir.kotlinMvpDagger2.remote.ServiceImpl
import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomePresenter
import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (AndroidModule::class), ActivityModule::class, FragmentModule::class])
interface ApplicationComponent {
    //Atividades e fragmentos que serao injetados os modulos
    fun inject(application: Application)

    fun inject(serviceImpl: ServiceImpl)

    fun inject(homePresenter: HomePresenter)

    fun inject(homeFragment: HomeFragment)
}