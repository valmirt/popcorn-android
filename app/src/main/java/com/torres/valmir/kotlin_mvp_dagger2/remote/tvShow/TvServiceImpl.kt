package com.torres.valmir.kotlin_mvp_dagger2.remote.tvShow

import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.model.ListCastCrew
import com.torres.valmir.kotlin_mvp_dagger2.model.ListTV
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class TvServiceImpl: TvServiceApi {

    @Inject
    lateinit var retrofit: Retrofit

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun getTvShow(callback: TvServiceApi.ServiceCallback<ListTV>, query: String, page: Int, language: String) {
        val call = retrofit
                .create(TvEndPoint::class.java)
                .searchTvShow(query = query, page = page, language = language)

        returningCall(call, callback)
    }

    override fun getPopularTV(callback: TvServiceApi.ServiceCallback<ListTV>, page: Int, language: String) {
        val call = retrofit
                .create(TvEndPoint::class.java)
                .popularTV(page = page, language = language)

        returningCall(call, callback)
    }

    override fun getTopRatedTV(callback: TvServiceApi.ServiceCallback<ListTV>, page: Int, language: String) {
        val call = retrofit
                .create(TvEndPoint::class.java)
                .topRatedTV(page = page, language = language)

        returningCall(call, callback)
    }

    override fun getTodaysTV(callback: TvServiceApi.ServiceCallback<ListTV>, page: Int, language: String) {
        val call = retrofit
                .create(TvEndPoint::class.java)
                .todaysTV(page = page, language = language)

        returningCall(call, callback)
    }

    override fun getTvShowId(callback: TvServiceApi.ServiceCallback<TvShow>, id: Int, language: String) {
        val call = retrofit
                .create(TvEndPoint::class.java)
                .detailTV(id = id, language = language)

        call.enqueue(object: Callback<TvShow>{
            override fun onFailure(call: Call<TvShow>?, t: Throwable?) {
                callback.onLoaded(TvShow())
            }

            override fun onResponse(call: Call<TvShow>?, response: Response<TvShow>?) {
                var result = TvShow()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    override fun getCastCrewTV(callback: TvServiceApi.ServiceCallback<ListCastCrew>, id: Int) {
        val call = retrofit
                .create(TvEndPoint::class.java)
                .getCastandCrewTV(id = id)

        call.enqueue(object : Callback<ListCastCrew>{
            override fun onFailure(call: Call<ListCastCrew>?, t: Throwable?) {
                callback.onLoaded(ListCastCrew())
            }

            override fun onResponse(call: Call<ListCastCrew>?, response: Response<ListCastCrew>?) {
                var result = ListCastCrew()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }

    private fun returningCall(call: Call<ListTV>, callback: TvServiceApi.ServiceCallback<ListTV>){
        call.enqueue(object: Callback<ListTV> {
            override fun onFailure(call: Call<ListTV>?, t: Throwable?) {
                callback.onLoaded(ListTV())
            }

            override fun onResponse(call: Call<ListTV>?, response: Response<ListTV>?) {
                var result = ListTV()
                response?.body()?.let {
                    result = it
                }
                response?.code()?.let {
                    result.code = it
                }
                callback.onLoaded(result)
            }
        })
    }
}