package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person

class DetailPersonPresenter: DetailPersonContract.Presenter {
    private lateinit var view: DetailPersonContract.View

    override fun attach(view: DetailPersonContract.View) {
        this.view = view
    }
}