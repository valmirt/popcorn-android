package com.example.valmir.kotlinMvpDagger2.ui.main.detail

import com.example.valmir.kotlinMvpDagger2.model.Movie
import com.example.valmir.kotlinMvpDagger2.ui.base.BaseContract

interface DetailContract {
    interface View: BaseContract.View{
        fun successResponse(movie: Movie)

        fun errorResponse(error: String)
    }

    interface Presenter: BaseContract.Presenter<DetailContract.View>{
        fun getDetails(id: Int)
    }
}