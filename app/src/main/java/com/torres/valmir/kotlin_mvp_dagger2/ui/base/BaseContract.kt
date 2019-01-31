package com.torres.valmir.kotlin_mvp_dagger2.ui.base

interface BaseContract {
    interface Presenter <T> {
        fun attach(view: T)
    }

    interface View {

    }
}