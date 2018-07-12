package com.example.valmir.kotlin_mvp_dagger2.remote


import com.example.valmir.kotlin_mvp_dagger2.model.ListMovies
import com.example.valmir.kotlin_mvp_dagger2.model.Movie

interface ServiceApi {
    interface ServiceCallback <T> {
        fun onLoaded (resposta: T)
    }

    fun getPopular(callback: ServiceCallback<ListMovies>, page: Int)

    fun getMovie(callback: ServiceCallback<ListMovies>, query: String, page: Int)

    fun getTopRated(callback: ServiceCallback<ListMovies>, page: Int)

    fun getUpComming(callback: ServiceCallback<ListMovies>, page: Int)

    fun getMovieId(callback: ServiceCallback<Movie>, id: Int)

}