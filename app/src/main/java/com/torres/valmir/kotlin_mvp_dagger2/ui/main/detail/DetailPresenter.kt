package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail

class DetailPresenter: DetailContract.Presenter {
    private lateinit var view: DetailContract.View

    override fun attach(view: DetailContract.View) {
        this.view = view
    }
}