package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting

import android.support.v4.app.Fragment
import com.torres.valmir.kotlin_mvp_dagger2.model.Cast
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface CastingContract {
    interface View: BaseContract.View {
        fun successResponse(casting: List<Cast>?)

        fun errorResponse(error: String)
    }

    interface Presenter: BaseContract.Presenter<CastingContract.View>{
        fun getCastCrewMovie(id: Int)

        fun getCastCrewTv(id: Int)

        fun getPerson(fragment: Fragment, id: Int, language: String)
    }
}