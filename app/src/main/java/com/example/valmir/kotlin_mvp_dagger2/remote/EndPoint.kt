package com.example.valmir.kotlin_mvp_dagger2.remote

import com.example.valmir.kotlin_mvp_dagger2.model.Movie
import com.example.valmir.kotlin_mvp_dagger2.model.ListMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoint {
    @GET("search/movie")
    fun search (@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
               @Query("language") language: String = "pt-BR",
               @Query("query") query: String,
               @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/popular")
    fun popular(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                     @Query("language") language: String = "pt-BR",
                     @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/{id_movie}")
    fun searchMovieId(@Path("id_movie") id: Int,
                      @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                      @Query("language") language: String = "pt-BR") : Call<Movie>

    @GET("movie/now_playing")
    fun topRated(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                       @Query("language") language: String = "pt-BR",
                       @Query("page") page: Int):Call<ListMovies>

    @GET("movie/upcoming")
    fun upComming(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                           @Query("language") language: String = "pt-BR",
                           @Query("page") page: Int) : Call<ListMovies>
}