package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListCastCrew
import com.torres.valmir.kotlin_mvp_dagger2.model.Person
import com.torres.valmir.kotlin_mvp_dagger2.remote.movie.MovieServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.person.PersonApi
import com.torres.valmir.kotlin_mvp_dagger2.remote.tv_show.TvServiceApi
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person.DetailPersonActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.PERSON
import javax.inject.Inject

class CastingPresenter: CastingContract.Presenter {
    private lateinit var view: CastingContract.View

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var apiMovie: MovieServiceApi

    @Inject
    lateinit var apiTv: TvServiceApi

    @Inject
    lateinit var apiPerson: PersonApi

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: CastingContract.View) {
        this.view = view
    }

    override fun getCastCrewMovie(id: Int) {
        apiMovie.getCastCrew(object : MovieServiceApi.ServiceCallback<ListCastCrew>{
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

    override fun getCastCrewTv(id: Int) {
        apiTv.getCastCrewTV(object : TvServiceApi.ServiceCallback<ListCastCrew>{
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

    override fun getPerson(fragment: Fragment, id: Int, language: String) {
        apiPerson.getDetailPerson(object : PersonApi.ServiceCallback<Person>{
            override fun onLoaded(response: Person) {
                when(response.code) {
                    200 -> {
                        val intent = Intent(fragment.activity, DetailPersonActivity::class.java)
                        intent.putExtra(PERSON, response)
                        fragment.activity?.startActivity(intent)
                    }
                    404 -> view.errorResponse(context.getString(R.string.error_404))
                    500 -> view.errorResponse(context.getString(R.string.error_500))
                    503 -> view.errorResponse(context.getString(R.string.error_503))
                    504 -> view.errorResponse(context.getString(R.string.error_504))
                    else -> view.errorResponse(context.getString(R.string.error_connection))
                }
            }
        }, id, language)
    }
}