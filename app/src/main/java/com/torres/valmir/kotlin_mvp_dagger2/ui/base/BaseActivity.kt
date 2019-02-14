package com.torres.valmir.kotlin_mvp_dagger2.ui.base

import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person.DetailPersonActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver.ReceiverActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings.SettingsActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash.SplashActivity

abstract class BaseActivity: AppCompatActivity() {

    init {
        injector()
    }

    private fun injector(){
        when (this) {
            is AboutActivity -> TMDBApplication.graph.inject(this)
            is DetailActivity -> TMDBApplication.graph.inject(this)
            is HomeActivity -> TMDBApplication.graph.inject(this)
            is SettingsActivity -> TMDBApplication.graph.inject(this)
            is SplashActivity -> TMDBApplication.graph.inject(this)
            is ReceiverActivity -> TMDBApplication.graph.inject(this)
            is DetailPersonActivity -> TMDBApplication.graph.inject(this)
        }
    }
}