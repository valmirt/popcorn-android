package com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.biography


class InfoPersonPresenter: InfoPersonContract.Presenter {

    private lateinit var view: InfoPersonContract.View

    override fun attach(view: InfoPersonContract.View) {
        this.view = view
    }
}