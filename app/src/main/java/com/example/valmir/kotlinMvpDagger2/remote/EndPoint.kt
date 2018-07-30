package com.example.valmir.kotlinMvpDagger2.remote

import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.model.ListMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoint {
    @GET("search/movie")
    fun search (@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
               @Query("language") language: String = "en-US",
               @Query("query") query: String,
               @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/popular")
    fun popular(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                     @Query("language") language: String = "en_US",
                     @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/{id_movie}")
    fun searchMovieId(@Path("id_movie") id: Int,
                      @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                      @Query("language") language: String = "en-US") : Call<Movie>

    @GET("movie/now_playing")
    fun nowPlaying(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                       @Query("language") language: String = "en-US",
                       @Query("page") page: Int):Call<ListMovies>

    @GET("movie/top_rated")
    fun topRated(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                   @Query("language") language: String = "en-US",
                   @Query("page") page: Int):Call<ListMovies>

    @GET("movie/upcoming")
    fun upComing(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                 @Query("language") language: String = "en-US",
                 @Query("page") page: Int) : Call<ListMovies>
}