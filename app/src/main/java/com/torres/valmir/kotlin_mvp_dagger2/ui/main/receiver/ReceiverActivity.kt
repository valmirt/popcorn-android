package com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import javax.inject.Inject

class ReceiverActivity : BaseActivity(), ReceiverContract.View {
    private var data: Uri? = null
    private lateinit var preferences: SharedPreferences
    private lateinit var language: String

    @Inject
    lateinit var mPresenter: ReceiverContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
        mPresenter.attach(this)
        preferences = getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
        preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)?.let {
            this.language = it
        }

        data = intent?.data

        data?.let { uriData->
            val uri = uriData.toString()
            if(uri.contains("tv")) {
                mPresenter.getDetailTv(uri.substring(30), language)
            } else if (uri.contains("movie")) {
                mPresenter.getDetailMovie(uri.substring(33), language)
            }
        }
    }

    override fun errorResponse(error: String) = mPresenter.sendToHome(this, error)

    override fun successResponseDetailMovie(movie: Movie) = mPresenter.sendToDetailMovie(this, movie)

    override fun successResponseDetailTvShow(tv: TvShow) = mPresenter.sendToDetailTv(this, tv)

}