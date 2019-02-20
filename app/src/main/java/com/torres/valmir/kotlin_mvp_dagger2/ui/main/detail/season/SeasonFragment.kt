package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.adapter.SeasonAdapter
import com.torres.valmir.kotlin_mvp_dagger2.model.TvShow
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseFragment
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import kotlinx.android.synthetic.main.fragment_casting_detail.*
import javax.inject.Inject

class SeasonFragment: BaseFragment(), SeasonContract.View {

    @Inject
    lateinit var mPresenter: SeasonContract.Presenter

    private var tv: TvShow? = null
    private lateinit var mListAdapter: SeasonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        mPresenter.attach(this)

        args?.let {
            tv = it.getSerializable(Constants.TVSHOW_OBJECT) as? TvShow
        }

        tv?.seasons?.let { list ->
            mListAdapter = SeasonAdapter(list, context!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_casting_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv?.seasons?.let {
            fillView()
        }
    }

    private fun fillView() {
        list_casting.layoutManager = LinearLayoutManager(context)
        list_casting.setHasFixedSize(true)
        list_casting.adapter = mListAdapter
    }
}