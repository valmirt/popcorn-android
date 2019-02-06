package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail

import com.torres.valmir.kotlin_mvp_dagger2.model.Trailer
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface DetailContract {
    interface View: BaseContract.View{
        fun setupTrailers(trailers: List<Trailer>?)

        fun errorResponse(error: String)
    }

    interface Presenter: BaseContract.Presenter<DetailContract.View>{
        fun getTrailersMovie(id: Int, language: String)

        fun getTrailersTV(id: Int, language: String)

        fun sendToYoutube(key: String)
    }
}