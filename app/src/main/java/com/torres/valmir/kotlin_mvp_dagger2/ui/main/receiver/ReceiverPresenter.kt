package com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.person.PersonServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.DetailActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.DetailPersonActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.ERROR_RC
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.PERSON
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TVSHOW_OBJECT
import javax.inject.Inject



class ReceiverPresenter: ReceiverContract.Presenter {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiMovie: MovieServiceApi

    @Inject
    lateinit var apiTV: TvServiceApi

    @Inject
    lateinit var apiPerson: PersonServiceApi

    private lateinit var view: ReceiverContract.View

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: ReceiverContract.View) {
        this.view = view
    }

    override fun sendToDetailMovie(activity: AppCompatActivity, movie: Movie) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(MOVIE_OBJECT, movie)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
        activity.finish()
    }

    override fun sendToDetailTv(activity: AppCompatActivity, tv: TvShow) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(TVSHOW_OBJECT, tv)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
        activity.finish()
    }

    override fun getDetailMovie(id: String, language: String) {
        apiMovie.getMovieId(object : MovieServiceApi.ServiceCallback<Movie>{
            override fun onLoaded(response: Movie) {
                when(response.code){
                    200 -> view.successResponseDetailMovie(response)
                    404 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_404))
                    500 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_500))
                    503 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_503))
                    504 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_504))
                    else -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_connection))
                }
            }
        }, id.toInt(), language)
    }

    override fun getDetailTv(id: String, language: String) {
        apiTV.getTvShowId(object : TvServiceApi.ServiceCallback<TvShow>{
            override fun onLoaded(response: TvShow) {
                when(response.code){
                    200 -> view.successResponseDetailTvShow(response)
                    404 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_404))
                    500 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_500))
                    503 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_503))
                    504 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_504))
                    else -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_connection))
                }
            }
        }, id.toInt(), language)
    }

    override fun getDetailPerson(id: String, language: String) {
        apiPerson.getDetailPerson(object : PersonServiceApi.ServiceCallback<Person>{
            override fun onLoaded(response: Person) {
                when(response.code){
                    200 -> view.successResponsePerson(response)
                    404 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_404))
                    500 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_500))
                    503 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_503))
                    504 -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_504))
                    else -> view.errorResponse(context.getString(com.torres.valmir.kotlin_mvp_dagger2.R.string.error_connection))
                }
            }
        }, id.toInt(), language)
    }

    override fun sendDetailPerson(activity: AppCompatActivity, person: Person) {
        val intent = Intent(activity, DetailPersonActivity::class.java)
        intent.putExtra(PERSON, person)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
        activity.finish()
    }

    override fun sendToHome(activity: AppCompatActivity, error: String) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(ERROR_RC, error)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
        activity.finish()
    }
}