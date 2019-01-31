package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail

import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface DetailContract {
    interface View: BaseContract.View{

    }

    interface Presenter: BaseContract.Presenter<DetailContract.View>{

    }
}