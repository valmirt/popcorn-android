package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.info

import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlinMvpDagger2.model.Movie
import com.torres.valmir.kotlinMvpDagger2.ui.base.BaseContract

interface InfoContract {
    interface View: BaseContract.View{
        fun successResponse (similarMovies: List<Movie>?)

        fun successResponseMorePages (similarMovies: List<Movie>?)

        fun responseDetail(movie: Movie)

        fun errorResponse (error:String)
    }

    interface Presenter: BaseContract.Presenter<InfoContract.View>{
        fun getSimilarMovies (id: Int, page: Int, language: String)

        fun getDetails(id: Int, language: String)

        fun swapActivity(activity: AppCompatActivity, movie: Movie)
    }
}