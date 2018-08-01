package com.example.valmir.kotlinMvpDagger2.remote


import com.example.valmir.kotlinMvpDagger2.model.ListMovies
import com.example.valmir.kotlinMvpDagger2.model.Movie

interface ServiceApi {
    interface ServiceCallback <T> {
        fun onLoaded (response: T)
    }

    fun getPopular(callback: ServiceCallback<ListMovies>, page: Int)

    fun getMovie(callback: ServiceCallback<ListMovies>, query: String, page: Int)

    fun getTopRated(callback: ServiceCallback<ListMovies>, page: Int)

    fun getUpComming(callback: ServiceCallback<ListMovies>, page: Int)

    fun getNowPlaying(callback: ServiceCallback<ListMovies>, page: Int)

    fun getMovieId(callback: ServiceCallback<Movie>, id: Int)

    fun getSimilarMovies(callback: ServiceCallback<ListMovies>, id: Int, page: Int)
}