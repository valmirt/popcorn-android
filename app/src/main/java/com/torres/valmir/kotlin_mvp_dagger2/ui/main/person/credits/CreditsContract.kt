package com.torres.valmir.kotlin_mvp_dagger2.ui.main.person.credits

import android.support.v4.app.Fragment
import com.torres.valmir.kotlin_mvp_dagger2.model.Entity
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface CreditsContract {
    interface View: BaseContract.View {
        fun successResponse (filmography: List<Entity>?)

        fun errorResponse (error: String)
    }

    interface Presenter: BaseContract.Presenter<CreditsContract.View> {
        fun getFilmography (id: Int, language: String)

        fun getEntity (id: Int, language: String, media: String, fragment: Fragment)
    }
}