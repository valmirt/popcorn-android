package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person

import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface DetailPersonContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<DetailPersonContract.View> {

    }
}