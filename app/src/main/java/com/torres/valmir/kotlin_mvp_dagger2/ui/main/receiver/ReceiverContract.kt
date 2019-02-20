package com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver

import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface ReceiverContract {
    interface View: BaseContract.View {
        fun errorResponse(error: String)

        fun successResponseDetailMovie(movie: Movie)

        fun successResponseDetailTvShow(tv: TvShow)

        fun successResponsePerson(person: Person)
    }

    interface Presenter: BaseContract.Presenter<ReceiverContract.View> {
        fun sendToDetailMovie(activity: AppCompatActivity, movie: Movie)

        fun sendToDetailTv(activity: AppCompatActivity, tv: TvShow)

        fun getDetailMovie(id: String, language: String)

        fun getDetailTv(id: String, language: String)

        fun getDetailPerson(id: String, language: String)

        fun sendDetailPerson(activity: AppCompatActivity, person: Person)

        fun sendToHome(activity: AppCompatActivity, error: String)
    }
}