package com.torres.valmir.kotlin_mvp_dagger2.ui.main.about

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface AboutContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<AboutContract.View>{
        fun sendToFirstLink(activity: AppCompatActivity)

        fun sendToSecondLink(activity: AppCompatActivity)
    }
}