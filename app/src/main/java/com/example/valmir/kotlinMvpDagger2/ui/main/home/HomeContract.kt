package com.example.valmir.kotlinMvpDagger2.ui.main.home

import android.support.v7.app.AppCompatActivity
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.ui.base.BaseContract

interface HomeContract {
    interface View: BaseContract.View {
        fun setLoading(isLoading: Boolean)

        fun responseSuccessful(movieList: List<Movie>?)

        fun responseSucessfulMorePages(movieList: List<Movie>?)

        fun responseDetail(movie: Movie)

        fun errorResponse(error: String)
    }

    interface Presenter: BaseContract.Presenter<HomeContract.View>{
        fun getPopular(page: Int)

        fun getNowPlaying(page: Int)

        fun getTopRated(page: Int)

        fun getMovie(query: String, page: Int)

        fun getDetails(id: Int)

        fun swapActivity(activity: AppCompatActivity, movie: Movie)
    }
}