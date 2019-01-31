package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list

import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface ListContract {
    interface View: BaseContract.View {
        fun setLoading(isLoading: Boolean)

        fun successResponseMovie(movieList: List<Movie>?)

        fun successResponseMorePagesMovie(movieList: List<Movie>?)

        fun successResponseDetailMovie(movie: Movie)

        fun errorResponse(error: String)

        fun successResponseTvShow(tvList: List<TvShow>?)

        fun successResponseMorePagesTvShow(tvList: List<TvShow>?)

        fun successResponseDetailTvShow(tv: TvShow)
    }

    interface Presenter: BaseContract.Presenter<View>{
        fun getPopularMovie(page: Int, language: String)

        fun getPopularTvShow(page: Int, language: String)

        fun getNowPlayingMovie(page: Int, language: String)

        fun getTodaysTvShow(page: Int, language: String)

        fun getTopRatedMovie(page: Int, language: String)

        fun getTopRatedTvShow(page: Int, language: String)

        fun getMovie(query: String, page: Int, language: String)

        fun getTvShow(query: String, page: Int, language: String)

        fun getDetailsMovie(id: Int, language: String)

        fun getDetailsTvShow(id: Int, language: String)

        fun swapActivity(origin: FragmentActivity?, activity: AppCompatActivity, movie: Movie?, tv: TvShow?)
    }
}