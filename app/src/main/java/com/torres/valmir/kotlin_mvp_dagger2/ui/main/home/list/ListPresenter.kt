package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListMovies
import com.torres.valmir.kotlin_mvp_dagger2.model.ListTV
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.tvShow.TvServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TVSHOW_OBJECT
import javax.inject.Inject

class ListPresenter: ListContract.Presenter {
    private lateinit var view: ListContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiMovie: MovieServiceApi

    @Inject
    lateinit var apiTv: TvServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: ListContract.View) {
        this.view = view
    }

    override fun getPopularMovie(page: Int, language: String) {
        view.setLoading(true)
        apiMovie.getPopular(object : MovieServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseMovie(response.movieList)
                        else view.successResponseMorePagesMovie(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page, language)
    }

    override fun getPopularTvShow(page: Int, language: String) {
        view.setLoading(true)
        apiTv.getPopularTV(object : TvServiceApi.ServiceCallback<ListTV>{
            override fun onLoaded(response: ListTV) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseTvShow(response.tvList)
                        else view.successResponseMorePagesTvShow(response.tvList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page, language)
    }

    override fun getNowPlayingMovie(page: Int, language: String) {
        view.setLoading(true)
        apiMovie.getNowPlaying(object : MovieServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseMovie(response.movieList)
                        else view.successResponseMorePagesMovie(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page, language)
    }

    override fun getTodaysTvShow(page: Int, language: String) {
        view.setLoading(true)
        apiTv.getTodaysTV(object : TvServiceApi.ServiceCallback<ListTV>{
            override fun onLoaded(response: ListTV) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseTvShow(response.tvList)
                        else view.successResponseMorePagesTvShow(response.tvList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page, language)
    }

    override fun getTopRatedMovie(page: Int, language: String) {
        view.setLoading(true)
        apiMovie.getTopRated(object : MovieServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseMovie(response.movieList)
                        else view.successResponseMorePagesMovie(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page, language)
    }

    override fun getTopRatedTvShow(page: Int, language: String) {
        view.setLoading(true)
        apiTv.getTopRatedTV(object : TvServiceApi.ServiceCallback<ListTV>{
            override fun onLoaded(response: ListTV) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseTvShow(response.tvList)
                        else view.successResponseMorePagesTvShow(response.tvList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, page, language)
    }

    override fun getMovie(query: String, page: Int, language: String) {
        view.setLoading(true)
        apiMovie.getMovie(object : MovieServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseMovie(response.movieList)
                        else view.successResponseMorePagesMovie(response.movieList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, query, page, language)
    }

    override fun getTvShow(query: String, page: Int, language: String) {
        view.setLoading(true)
        apiTv.getTvShow(object : TvServiceApi.ServiceCallback<ListTV>{
            override fun onLoaded(response: ListTV) {
                view.setLoading(false)
                when(response.code){
                    200 -> {
                        if (page == 1) view.successResponseTvShow(response.tvList)
                        else view.successResponseMorePagesTvShow(response.tvList)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, query, page, language)
    }

    override fun getDetailsMovie(id: Int, language: String) {
        view.setLoading(true)
        apiMovie.getMovieId(object : MovieServiceApi.ServiceCallback<Movie>{
            override fun onLoaded(response: Movie) {
                view.setLoading(false)
                when(response.code){
                    200 -> view.successResponseDetailMovie(response)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }

    override fun getDetailsTvShow(id: Int, language: String) {
        view.setLoading(true)
        apiTv.getTvShowId(object : TvServiceApi.ServiceCallback<TvShow>{
            override fun onLoaded(response: TvShow) {
                view.setLoading(false)
                when(response.code){
                    200 -> view.successResponseDetailTvShow(response)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }

    override fun swapActivity(origin: FragmentActivity?, activity: AppCompatActivity, movie: Movie?, tv: TvShow?) {
        val intent = Intent(origin, activity::class.java)
        movie?.let {
            intent.putExtra(MOVIE_OBJECT, it)
        }
        tv?.let {
            intent.putExtra(TVSHOW_OBJECT, it)
        }
        origin?.startActivity(intent)
    }
}
