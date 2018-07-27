package com.example.valmir.kotlinMvpDagger2.ui.main.home

import com.example.valmir.kotlinMvpDagger2.TMDBApplication
import com.example.valmir.kotlinMvpDagger2.remote.ServiceApi
import javax.inject.Inject

class HomePresenter: HomeContract.Presenter {

    private lateinit var view: HomeContract.View

    @Inject
    lateinit var api: ServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun getPopular(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNowPlaying(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTopRated(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpComing(page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovie(query: String, page: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDetails(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}