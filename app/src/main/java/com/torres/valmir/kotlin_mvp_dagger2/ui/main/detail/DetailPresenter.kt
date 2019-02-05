package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail

import android.content.Context
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListTrailers
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import javax.inject.Inject

class DetailPresenter: DetailContract.Presenter {
    private lateinit var view: DetailContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiMovie: MovieServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun getTrailersMovie(id: Int, language: String) {
        apiMovie.getTrailer(object : MovieServiceApi.ServiceCallback<ListTrailers>{
            override fun onLoaded(response: ListTrailers) {
                when(response.code){
                    200 -> {
                        view.setupTrailers(response.trailerList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }

    override fun getTrailersTV(id: Int, language: String) {

    }
}