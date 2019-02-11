package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListTrailers
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.SHARE_URL
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.YOUTUBE_URL
import javax.inject.Inject

class DetailPresenter: DetailContract.Presenter {
    private lateinit var view: DetailContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiMovie: MovieServiceApi

    @Inject
    lateinit var apiTV: TvServiceApi

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
        apiTV.getTrailer(object : TvServiceApi.ServiceCallback<ListTrailers> {
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

    override fun shareMovieOrTvShow(id: Int, type: String) {
        val message = context.getString(R.string.share_message) +
                " " +
                SHARE_URL +
                type +
                "/" +
                id
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.type = "text/plain"

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)+ " " + type))
    }

    override fun sendToHomeActivity(activity: AppCompatActivity) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    override fun sendToYoutube(key: String) {
        val uri = Uri.parse(YOUTUBE_URL+key)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}