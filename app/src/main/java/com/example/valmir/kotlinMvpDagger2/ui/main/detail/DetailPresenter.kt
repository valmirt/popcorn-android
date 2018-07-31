package com.example.valmir.kotlinMvpDagger2.ui.main.detail

import android.content.Context
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.TMDBApplication
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.remote.ServiceApi
import javax.inject.Inject

class DetailPresenter: DetailContract.Presenter {
    private lateinit var view: DetailContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var api: ServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun getDetails(id: Int) {
        api.getMovieId(object : ServiceApi.ServiceCallback<Movie>{
            override fun onLoaded(response: Movie) {
                when(response.code){
                    200 -> view.successResponse(response)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id)
    }
}