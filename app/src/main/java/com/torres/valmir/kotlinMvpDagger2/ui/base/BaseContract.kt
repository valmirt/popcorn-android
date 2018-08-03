package com.torres.valmir.kotlinMvpDagger2.ui.base

interface BaseContract {
    interface Presenter <T> {
        fun attach(view: T)
    }

    interface View {

    }
}