package com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver

import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface ReceiverContract {
    interface View: BaseContract.View {
        fun errorResponse(error: String)

        fun successResponseDetailMovie(movie: Movie)

        fun successResponseDetailTvShow(tv: TvShow)
    }

    interface Presenter: BaseContract.Presenter<ReceiverContract.View> {
        fun sendToDetailMovie(tv: TvShow)

        fun sendToDetailTv(movie: Movie)

        fun getDetailMovie(id: String)

        fun getDetailTv(id: String)
    }
}