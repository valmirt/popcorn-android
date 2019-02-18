package com.torres.valmir.kotlin_mvp_dagger2.remote.person

import com.torres.valmir.kotlin_mvp_dagger2.model.Entity
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonEndpoint {

    @GET("person/{person_id}")
    fun getDetail (@Path("person_id") id: Int,
                   @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                   @Query("language") language: String): Call<Person>

    @GET("person/{person_id}/combined_credits")
    fun getCredits (@Path("person_id") id: Int,
                    @Query("api_key") key: String = "ebf3f29bcec9455240223a565fb2a81d",
                    @Query("language") language: String): Call<Entity>
}