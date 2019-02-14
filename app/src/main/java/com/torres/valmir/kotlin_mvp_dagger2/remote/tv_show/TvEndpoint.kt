package com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show

import com.torres.valmir.kotlin_mvp_dagger2.model.ListCastCrew
import com.torres.valmir.kotlin_mvp_dagger2.model.ListTV
import com.torres.valmir.kotlin_mvp_dagger2.model.ListTrailers
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvEndpoint {

    @GET("search/tv")
    fun searchTvShow(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                     @Query("language") language: String,
                     @Query("query") query: String,
                     @Query("page") page: Int) : Call<ListTV>

    @GET("tv/{tv_id}")
    fun detailTV(@Path("tv_id") id: Int,
                 @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                 @Query("language") language: String) : Call<TvShow>

    @GET("tv/popular")
    fun popularTV(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                  @Query("language") language: String,
                  @Query("page") page: Int) : Call<ListTV>

    @GET("tv/top_rated")
    fun topRatedTV(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                   @Query("language") language: String,
                   @Query("page") page: Int) : Call<ListTV>

    @GET("tv/on_the_air")
    fun todaysTV(@Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                 @Query("language") language: String,
                 @Query("page") page: Int) : Call<ListTV>

    @GET("tv/{tv_id}/credits")
    fun getCastandCrewTV(@Path("tv_id") id: Int,
                         @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d") : Call<ListCastCrew>

    @GET("tv/{tv_id}/videos")
    fun getTrailerTV(@Path("tv_id") id: Int,
                     @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                     @Query("language") language: String) : Call<ListTrailers>
}