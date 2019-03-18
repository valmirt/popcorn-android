package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.adapter.CastingAdapter
import com.torres.valmir.kotlin_mvp_dagger2.adapter.ItemListener
import com.torres.valmir.kotlin_mvp_dagger2.model.Cast
import com.torres.valmir.kotlin_mvp_dagger2.model.Movie
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseFragment
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.MOVIE_OBJECT
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TVSHOW_OBJECT
import kotlinx.android.synthetic.main.fragment_casting_detail.*
import javax.inject.Inject

class CastingFragment: BaseFragment(), CastingContract.View {
    private lateinit var mListAdapter: CastingAdapter
    private var movie: Movie? = null
    private var tv: TvShow? = null
    private var language = ""
    private lateinit var preferences: SharedPreferences

    @Inject
    lateinit var mPresenter: CastingContract.Presenter

    private val itemListener = object : ItemListener<Cast> {
        override fun onClick(item: Cast) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                mPresenter.getPerson(this@CastingFragment, item.id, language)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attach(this)
        val args = arguments
        mListAdapter = CastingAdapter(context!!, itemListener)

        activity?.let {
            preferences = it.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)
            preferences.getString(Constants.LANGUAGE, Constants.ENGLISH_LANGUAGE)?.let { language->
                this.language = language
            }
        }

        args?.let {
            movie = it.getSerializable(MOVIE_OBJECT) as? Movie
            tv = it.getSerializable(TVSHOW_OBJECT) as? TvShow
        }
    }

    override fun onResume() {
        super.onResume()
        movie?.let {
            mPresenter.getCastCrewMovie(it.id)
        }

        tv?.let {
            mPresenter.getCastCrewTv(it.id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_casting_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        list_casting.setHasFixedSize(true)
        list_casting.layoutManager = LinearLayoutManager(context)
        list_casting.adapter = mListAdapter
    }

    override fun successResponse(casting: List<Cast>?) {
        casting?.let {list->
            val temp = list.sortedBy { it.order }
            mListAdapter.replaceData(temp)
        }
    }

    override fun errorResponse(error: String){
        view?.let {
            Snackbar.make(it, error, Snackbar.LENGTH_LONG).show()
        }
    }
}