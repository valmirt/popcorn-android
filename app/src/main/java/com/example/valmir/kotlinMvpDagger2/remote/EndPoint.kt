package com.example.valmir.kotlinMvpDagger2.remote

import com.example.valmir.kotlinMvpDagger2.model.ListCastCrew
import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.model.ListMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoint {
    @GET("search/movie")
    fun search (@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
               @Query("language") language: String,
               @Query("query") query: String,
               @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/popular")
    fun popular(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                     @Query("language") language: String,
                     @Query("page") page: Int) : Call<ListMovies>

    @GET("movie/{movie_id}")
    fun searchMovieId(@Path("movie_id") id: Int,
                      @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                      @Query("language") language: String) : Call<Movie>

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
    fun getCastandCrew(@Path("movie_id") id: Int,
                       @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d") : Call<ListCastCrew>
}