package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.casting

import com.torres.valmir.kotlinMvpDagger2.model.Cast
import com.torres.valmir.kotlinMvpDagger2.ui.base.BaseContract

interface CastingContract {
    interface View: BaseContract.View {
        fun successResponse(casting: List<Cast>?)

        fun errorResponse(error: String)
    }

    interface Presenter: BaseContract.Presenter<CastingContract.View>{
        fun getCastCrewMovie(id: Int)

        fun getCastCrewTv(id: Int)
    }
}