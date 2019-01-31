package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season

class SeasonPresenter: SeasonContract.Presenter {
    private lateinit var view: SeasonContract.View

    override fun attach(view: SeasonContract.View) {
        this.view = view
    }
}