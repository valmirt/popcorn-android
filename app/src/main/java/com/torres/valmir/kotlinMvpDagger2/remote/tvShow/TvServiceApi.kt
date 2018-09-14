package com.torres.valmir.kotlinMvpDagger2.remote.tvShow

import com.torres.valmir.kotlinMvpDagger2.model.ListCastCrew
import com.torres.valmir.kotlinMvpDagger2.model.ListMovies
import com.torres.valmir.kotlinMvpDagger2.model.ListTV
import com.torres.valmir.kotlinMvpDagger2.model.TvShow
import com.torres.valmir.kotlinMvpDagger2.remote.movie.MovieServiceApi

interface TvServiceApi {
    interface ServiceCallback <T> {
        fun onLoaded (response: T)
    }

    fun getPopularTV(callback: ServiceCallback<ListTV>, page: Int, language: String)

    fun getTopRatedTV(callback: ServiceCallback<ListTV>, page: Int, language: String)

    fun getTodaysTV(callback: ServiceCallback<ListTV>, page: Int, language: String)

    fun getTvShowId(callback: ServiceCallback<TvShow>, id: Int, language: String)

    fun getCastCrewTV(callback: ServiceCallback<ListCastCrew>, id: Int)

    fun getTvShow(callback: TvServiceApi.ServiceCallback<ListTV>, query: String, page: Int, language: String)
}