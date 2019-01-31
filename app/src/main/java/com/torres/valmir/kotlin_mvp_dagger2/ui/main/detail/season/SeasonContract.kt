package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season

import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface SeasonContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<SeasonContract.View>{

    }
}