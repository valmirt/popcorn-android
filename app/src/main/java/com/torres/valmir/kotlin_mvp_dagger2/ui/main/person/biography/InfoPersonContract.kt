package com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.biography

import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface InfoPersonContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<InfoPersonContract.View> {

    }
}