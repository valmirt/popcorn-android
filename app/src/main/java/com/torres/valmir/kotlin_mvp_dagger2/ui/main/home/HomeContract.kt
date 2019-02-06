package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home

import android.content.Context
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface HomeContract {
    interface View: BaseContract.View {
        fun showWelcomeMessage(isVisible: Boolean)
    }

    interface Presenter: BaseContract.Presenter<HomeContract.View>{
        fun swapListFragment(activity: BaseActivity, fragment: Int)

        fun setActivitySettings()

        fun setActivityAbout()

        fun getPreference(activity: BaseActivity)

        fun setPreference(activity: BaseActivity)
    }
}