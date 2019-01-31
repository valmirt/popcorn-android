package com.torres.valmir.kotlin_mvp_dagger2.ui.main.about

import android.content.Context
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface AboutContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<AboutContract.View>{
        fun sendToFirstLink(context: Context)

        fun sendToSecondLink(context: Context)
    }
}