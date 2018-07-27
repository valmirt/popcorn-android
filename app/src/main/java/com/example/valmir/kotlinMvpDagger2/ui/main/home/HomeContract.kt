package com.example.valmir.kotlinMvpDagger2.ui.main.home


import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.ui.base.BaseContract

interface HomeContract {
    interface View: BaseContract.View {
        fun setLoading(isLoading: Boolean)

        fun responseSuccessful()

        fun errorResponse(error: String)

        fun movieDetails(movie: Movie)
    }

    interface Presenter: BaseContract.Presenter<HomeContract.View>{
        fun getPopular(page: Int)

        fun getNowPlaying(page: Int)

        fun getTopRated(page: Int)

        fun getUpComing(page: Int)

        fun getMovie(query: String, page: Int)

        fun getDetails(id: Int)
    }
}