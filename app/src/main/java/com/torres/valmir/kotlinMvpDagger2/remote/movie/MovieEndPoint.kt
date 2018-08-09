package com.torres.valmir.kotlinMvpDagger2.remote.movie

import com.torres.valmir.kotlinMvpDagger2.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieEndPoint {

    @GET("search/movie")
    fun searchMovie (@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                     @Query("language") language: String,
                     @Query("query") query: String,
                     @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/{movie_id}")
    fun searchMovieId(@Path("movie_id") id: Int,
                      @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                      @Query("language") language: String) : Call<Movie>

    @GET("movie/popular")
    fun popular(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                @Query("language") language: String,
                @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/now_playing")
    fun nowPlaying(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                   @Query("language") language: String,
                   @Query("page") page: Int):Call<ListMovies>

    @GET("movie/top_rated")
    fun topRated(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                   @Query("language") language: String,
                   @Query("page") page: Int):Call<ListMovies>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") id: Int,
                         @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                         @Query("language") language: String,
                         @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/{movie_id}/credits")
    fun getCastandCrewMovie(@Path("movie_id") id: Int,
                            @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d") : Call<ListCastCrew>

}