package com.torres.valmir.kotlin_mvp_dagger2.ui.main.receiver

import android.net.Uri
import android.os.Bundle
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import javax.inject.Inject

class ReceiverActivity : BaseActivity(), ReceiverContract.View {
    private var data: Uri? = null

    @Inject
    lateinit var mPresenter: ReceiverContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        data = intent?.data

        data?.let { uriData->
            val uri = uriData.toString()
            if(uri.contains("tv")) {
                mPresenter.getDetailTv(uri.substring(30))
            } else if (uri.contains("movie")) {
                mPresenter.getDetailMovie(uri.substring(33))
            }
        }
    }

    override fun errorResponse(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun successResponseDetailMovie(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun successResponseDetailTvShow(tv: TvShow) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}