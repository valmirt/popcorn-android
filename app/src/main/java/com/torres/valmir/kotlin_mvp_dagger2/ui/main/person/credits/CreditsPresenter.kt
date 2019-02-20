package com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.credits

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListEntity
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.person.PersonServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import javax.inject.Inject

class CreditsPresenter: CreditsContract.Presenter {

    private lateinit var view: CreditsContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiPerson: PersonServiceApi

    @Inject
    lateinit var apiMovie: MovieServiceApi

    @Inject
    lateinit var apiTV: TvServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: CreditsContract.View) {
        this.view = view
    }

    override fun getFilmography(id: Int, language: String) {
        apiPerson.getCreditsPerson(object : PersonServiceApi.ServiceCallback<ListEntity>{
            override fun onLoaded(response: ListEntity) {
                when(response.code){
                    200 -> view.successResponse(response.entityList)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }

    override fun getEntity(id: Int, language: String, media: String, fragment: Fragment) {
        when (media) {
            "tv" -> {
                apiTV.getTvShowId(object : TvServiceApi.ServiceCallback<TvShow>{
                    override fun onLoaded(response: TvShow) {
                        when(response.code){
                            200 -> sendToDetail(tv = response, fragment = fragment)
                            404 -> view.errorResponse(context.getString(R.string.error_404))
                            500 -> view.errorResponse(context.getString(R.string.error_500))
                            503 -> view.errorResponse(context.getString(R.string.error_503))
                            504 -> view.errorResponse(context.getString(R.string.error_504))
                            else -> view.errorResponse(context.getString(R.string.error_connection))
                        }
                    }
                }, id, language)
            }
            "movie" -> {
                apiMovie.getMovieId(object : MovieServiceApi.ServiceCallback<Movie>{
                    override fun onLoaded(response: Movie) {
                        when(response.code){
                            200 -> sendToDetail(movie = response, fragment = fragment)
                            404 -> view.errorResponse(context.getString(R.string.error_404))
                            500 -> view.errorResponse(context.getString(R.string.error_500))
                            503 -> view.errorResponse(context.getString(R.string.error_503))
                            504 -> view.errorResponse(context.getString(R.string.error_504))
                            else -> view.errorResponse(context.getString(R.string.error_connection))
                        }
                    }
                }, id, language)
            }
            else -> view.errorResponse(context.getString(R.string.error_404))
        }
    }

    private fun sendToDetail(movie: Movie? = null, tv: TvShow? = null, fragment: Fragment) {
        val intent = Intent(fragment.activity, DetailActivity::class.java)
        movie?.let {
            intent.putExtra(Constants.MOVIE_OBJECT, it)
        }
        tv?.let {
            intent.putExtra(Constants.TVSHOW_OBJECT, it)
        }
        fragment.activity?.startActivity(intent)
    }
}