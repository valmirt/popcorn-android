package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person

import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface DetailPersonContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<DetailPersonContract.View> {
        fun sharePerson(id: Int, type: String)

        fun sendToHomeActivity(activity: AppCompatActivity)
    }
}