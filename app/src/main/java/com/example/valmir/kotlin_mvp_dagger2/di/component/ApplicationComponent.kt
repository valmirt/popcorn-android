package com.example.valmir.kotlin_mvp_dagger2.di.component

import android.app.Application
import com.example.valmir.kotlin_mvp_dagger2.di.module.ActivityModule
import com.example.valmir.kotlin_mvp_dagger2.di.module.AndroidModule
import com.example.valmir.kotlin_mvp_dagger2.di.module.FragmentModule
import com.example.valmir.kotlin_mvp_dagger2.di.module.NetModule
import com.example.valmir.kotlin_mvp_dagger2.remote.ServiceImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (AndroidModule::class), ActivityModule::class, FragmentModule::class])
interface ApplicationComponent {
    //Atividades e fragmentos que serao injetados os modulos
    fun inject(application: Application)
    fun inject(serviceImpl: ServiceImpl)

}