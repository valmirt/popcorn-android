package com.torres.valmir.kotlinMvpDagger2.ui.main.detail.season

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.adapter.SeasonAdapter
import com.torres.valmir.kotlinMvpDagger2.model.TvShow
import com.torres.valmir.kotlinMvpDagger2.util.Constants
import kotlinx.android.synthetic.main.fragment_season_detail.*

class SeasonFragment: Fragment() {
    private var tv: TvShow? = null
    private lateinit var mListAdapter: SeasonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments

        args?.let {
            tv = it.getSerializable(Constants.TVSHOW_OBJECT) as? TvShow
        }

        tv?.seasons?.let { list ->
            mListAdapter = SeasonAdapter(list, context!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_season_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv?.seasons?.let {
            fillView()
        }
    }

    private fun fillView() {
        list_seasons.layoutManager = LinearLayoutManager(context)
        list_seasons.setHasFixedSize(true)
        list_seasons.adapter = mListAdapter
    }
}