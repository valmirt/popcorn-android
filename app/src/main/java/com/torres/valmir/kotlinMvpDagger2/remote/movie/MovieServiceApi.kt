package com.torres.valmir.kotlinMvpDagger2.remote.movie

import com.torres.valmir.kotlinMvpDagger2.model.ListCastCrew
import com.torres.valmir.kotlinMvpDagger2.model.ListMovies
import com.torres.valmir.kotlinMvpDagger2.model.Movie


interface MovieServiceApi {
    interface ServiceCallback <T> {
        fun onLoaded (response: T)
    }

    fun getPopular(callback: ServiceCallback<ListMovies>, page: Int, language: String)

    fun getMovie(callback: ServiceCallback<ListMovies>, query: String, page: Int, language: String)

    fun getTopRated(callback: ServiceCallback<ListMovies>, page: Int, language: String)

    fun getNowPlaying(callback: ServiceCallback<ListMovies>, page: Int, language: String)

    fun getMovieId(callback: ServiceCallback<Movie>, id: Int, language: String)

    fun getSimilarMovies(callback: ServiceCallback<ListMovies>, id: Int, page: Int, language: String)

    fun getCastCrew(callback: ServiceCallback<ListCastCrew>, id: Int)
}