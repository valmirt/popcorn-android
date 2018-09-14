package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.season

import com.torres.valmir.kotlinMvpDagger2.ui.base.BaseContract

interface SeasonContract {
    interface View: BaseContract.View {

    }

    interface Presenter: BaseContract.Presenter<SeasonContract.View>{

    }
}