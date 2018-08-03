package com.example.valmir.kotlinMvpDagger2.ui.main.detail.info

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.TMDBApplication
import com.example.valmir.kotlinMvpDagger2.model.ListMovies
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.remote.ServiceApi
import com.example.valmir.kotlinMvpDagger2.util.Constants.Companion.MOVIE_OBJECT
import javax.inject.Inject

class InfoPresenter: InfoContract.Presenter {
    private lateinit var view: InfoContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var api: ServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: InfoContract.View) {
        this.view = view
    }

    override fun getSimilarMovies(id: Int, page: Int, language: String) {
        api.getSimilarMovies(object : ServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponse(response.movieList)
                        else view.successResponseMorePages(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, page, language)
    }

    override fun getDetails(id: Int, language: String) {
        api.getMovieId(object : ServiceApi.ServiceCallback<Movie>{
            override fun onLoaded(response: Movie) {
                when(response.code){
                    200 -> view.responseDetail(response)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }

    override fun swapActivity(activity: AppCompatActivity, movie: Movie) {
        val intent = Intent(context, activity::class.java)
        intent.putExtra(MOVIE_OBJECT, movie)
        context.startActivity(intent)
    }
}