package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.casting

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.TMDBApplication
import com.torres.valmir.kotlinMvpDagger2.adapter.CastingAdapter
import com.torres.valmir.kotlinMvpDagger2.model.Cast
import com.torres.valmir.kotlinMvpDagger2.model.Movie
import com.torres.valmir.kotlinMvpDagger2.util.Constants
import kotlinx.android.synthetic.main.fragment_casting_detail.*
import javax.inject.Inject

class CastingFragment: Fragment(), CastingContract.View {
    private lateinit var mListAdapter: CastingAdapter
    private var movie: Movie? = null

    @Inject
    lateinit var mPresenter: CastingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TMDBApplication.graph.inject(this)
        mPresenter.attach(this)
        val args = arguments
        mListAdapter = CastingAdapter(ArrayList(0))

        args?.let {
            movie = it.getSerializable(Constants.MOVIE_OBJECT) as Movie
        }
    }

    override fun onResume() {
        super.onResume()
        movie?.let {
            mPresenter.getCastCrew(it.id)
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

    override fun errorResponse(error: String) = Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).show()
}