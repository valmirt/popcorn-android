package com.example.valmir.kotlinMvpDagger2.ui.main.home

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

class HomePresenter: HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var api: ServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun getPopular(page: Int) {
        view.setLoading(true)
        api.getPopular(object : ServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.responseSuccessful(response.movieList)
                        else view.responseSucessfulMorePages(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page)
    }

    override fun getNowPlaying(page: Int) {
        view.setLoading(true)
        api.getNowPlaying(object : ServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.responseSuccessful(response.movieList)
                        else view.responseSucessfulMorePages(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page)
    }

    override fun getTopRated(page: Int) {
        view.setLoading(true)
        api.getTopRated(object : ServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.responseSuccessful(response.movieList)
                        else view.responseSucessfulMorePages(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page)
    }

    override fun getUpComing(page: Int) {
        view.setLoading(true)
        api.getUpComming(object : ServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.responseSuccessful(response.movieList)
                        else view.responseSucessfulMorePages(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page)
    }

    override fun getMovie(query: String, page: Int) {
        view.setLoading(true)
        api.getMovie(object : ServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.responseSuccessful(response.movieList)
                        else view.responseSucessfulMorePages(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, query, page)
    }

    override fun getDetails(id: Int) {
        view.setLoading(true)
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
        }, id)
    }

    override fun swapActivity(activity: AppCompatActivity, movie: Movie) {
        val intent = Intent(context, activity::class.java)
        intent.putExtra(MOVIE_OBJECT, movie)
        context.startActivity(intent)
    }
}
