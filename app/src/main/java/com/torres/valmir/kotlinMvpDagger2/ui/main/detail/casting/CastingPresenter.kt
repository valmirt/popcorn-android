package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.casting

import android.content.Context
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.TMDBApplication
import com.torres.valmir.kotlinMvpDagger2.model.ListCastCrew
import com.torres.valmir.kotlinMvpDagger2.remote.ServiceApi
import javax.inject.Inject

class CastingPresenter: CastingContract.Presenter {
    private lateinit var view: CastingContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var api: ServiceApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: CastingContract.View) {
        this.view = view
    }

    override fun getCastCrew(id: Int) {
        api.getCastCrew(object : ServiceApi.ServiceCallback<ListCastCrew>{
            override fun onLoaded(response: ListCastCrew) {
                when(response.code){
                    200 -> view.successResponse(response.castList)
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id)
    }
}