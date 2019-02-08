package com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver

import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow

class ReceiverPresenter: ReceiverContract.Presenter {


    override fun sendToDetailMovie(tv: TvShow) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendToDetailTv(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDetailMovie(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDetailTv(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attach(view: ReceiverContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}