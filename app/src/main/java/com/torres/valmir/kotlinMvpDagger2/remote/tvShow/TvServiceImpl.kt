package com.torres.valmir.kotlinMvpDagger2.remote.tvShow

import com.torres.valmir.kotlinMvpDagger2.model.ListCastCrew
import com.torres.valmir.kotlinMvpDagger2.model.ListTV
import com.torres.valmir.kotlinMvpDagger2.model.TvShow
import com.torres.valmir.kotlinMvpDagger2.remote.movie.MovieServiceApi

class TvServiceImpl: TvServiceApi {

    override fun getPopularTV(callback: TvServiceApi.ServiceCallback<ListTV>, page: Int, language: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTopRatedTV(callback: TvServiceApi.ServiceCallback<ListTV>, page: Int, language: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTodaysTV(callback: TvServiceApi.ServiceCallback<ListTV>, page: Int, language: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTvShowId(callback: TvServiceApi.ServiceCallback<TvShow>, id: Int, language: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSimilarTV(callback: TvServiceApi.ServiceCallback<ListTV>, id: Int, page: Int, language: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCastCrewTV(callback: TvServiceApi.ServiceCallback<ListCastCrew>, id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSimilarTV(callback: MovieServiceApi.ServiceCallback<ListTV>, id: Int, page: Int, language: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}