package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListMovies
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TVSHOW_OBJECT
import javax.inject.Inject

class InfoPresenter: InfoContract.Presenter {
    private lateinit var view: InfoContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiMovie: MovieServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: InfoContract.View) {
        this.view = view
    }

    override fun getSimilarMovies(id: Int, page: Int, language: String) {
        apiMovie.getSimilarMovies(object : MovieServiceApi.ServiceCallback<ListMovies>{
            override fun onLoaded(response: ListMovies) {
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
        }, id, page, language)
    }

    override fun getDetails(id: Int, language: String) {
        apiMovie.getMovieId(object : MovieServiceApi.ServiceCallback<Movie>{
            override fun onLoaded(response: Movie) {
                when(response.code){
                    200 -> view.responseDetailMovie(response)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }

    override fun sendToDetailActivity(fragment: Fragment?, movie: Movie?, tv: TvShow?) {
        fragment?.let { frag->
            val intent = Intent(frag.activity, DetailActivity::class.java)
            movie?.let { intent.putExtra(MOVIE_OBJECT, it) }
            tv?.let { intent.putExtra(TVSHOW_OBJECT, it) }
            frag.activity?.startActivity(intent)
        }
    }
}