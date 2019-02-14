package com.torres.valmir.kotlin_mvp_dagger2.remote.person

import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.Entity
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PersonImpl: PersonApi {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun getDetailPerson(callback: PersonApi.ServiceCallback<Person>, id: Int, language: String) {
        val call = retrofit
                .create(PersonEndpoint::class.java)
                .getDetail(id = id, language = language)

        call.enqueue(object : Callback<Person>{
            override fun onFailure(call: Call<Person>, t: Throwable) {
                callback.onLoaded(Person())
            }

            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                var result = Person()

                response.body()?.let {
                    result = it
                }
                result.code = response.code()

                callback.onLoaded(result)
            }
        })
    }

    override fun getCreditsPerson(callback: PersonApi.ServiceCallback<Entity>, id: Int, language: String) {
        val call = retrofit
                .create(PersonEndpoint::class.java)
                .getCredits(id = id, language = language)

        call.enqueue(object : Callback<Entity>{
            override fun onFailure(call: Call<Entity>, t: Throwable) {
                callback.onLoaded(Entity())
            }

            override fun onResponse(call: Call<Entity>, response: Response<Entity>) {
                var result = Entity()

                response.body()?.let {
                    result = it
                }
                result.code = response.code()

                callback.onLoaded(result)
            }
        })
    }
}