package com.torres.valmir.kotlin_mvp_dagger2

import android.app.Application
import com.torres.valmir.kotlin_mvp_dagger2.di.component.ApplicationComponent
import com.torres.valmir.kotlin_mvp_dagger2.di.component.DaggerApplicationComponent
import com.torres.valmir.kotlin_mvp_dagger2.di.module.ActivityModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.AndroidModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.FragmentModule
import com.torres.valmir.kotlin_mvp_dagger2.di.module.NetModule


class TMDBApplication: Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApplicationComponent
                .builder()
                .androidModule(AndroidModule(this))
                .netModule(NetModule())
                .activityModule(ActivityModule())
                .fragmentModule(FragmentModule())
                .build()

        graph.inject(this)
    }
}