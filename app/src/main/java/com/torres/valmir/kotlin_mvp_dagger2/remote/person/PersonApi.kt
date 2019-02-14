package com.torres.valmir.kotlin_mvp_dagger2.remote.person

import com.torres.valmir.kotlin_mvp_dagger2.model.Entity
import com.torres.valmir.kotlin_mvp_dagger2.model.Person

interface PersonApi {
    interface ServiceCallback <T> {
        fun onLoaded (response: T)
    }

    fun getDetailPerson(callback: ServiceCallback<Person>, id: Int, language: String)

    fun getCreditsPerson(callback: ServiceCallback<Entity>, id: Int, language: String)
}