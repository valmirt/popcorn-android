package com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash

import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface SplashContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<SplashContract.View> {
        fun setActivityHome(activity: BaseActivity)
    }
}