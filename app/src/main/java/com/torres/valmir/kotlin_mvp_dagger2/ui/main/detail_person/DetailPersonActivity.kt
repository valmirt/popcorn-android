package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.AppBarStateChangeListener
import com.torres.valmir.kotlin_mvp_dagger2.utils.Utils
import kotlinx.android.synthetic.main.activity_person.*
import javax.inject.Inject
import com.torres.valmir.kotlin_mvp_dagger2.R


class DetailPersonActivity: BaseActivity(), DetailPersonContract.View {
    private lateinit var mAppBarStateChangeListener: AppBarStateChangeListener

    @Inject
    lateinit var mPresenter: DetailPersonContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_info_person)
        setUpToolbar()
        setUpAmazingAvatar()
        mPresenter.attach(this)
    }

    private fun setUpToolbar() {
        app_bar.layoutParams.height = Utils.getDisplayMetrics(this).widthPixels * 9 / 16;
        app_bar.requestLayout()

        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setUpAmazingAvatar() {
        mAppBarStateChangeListener = object : AppBarStateChangeListener() {

            override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {}

            override fun onOffsetChanged(state: State?, offset: Float) {
                translationView(offset)
            }
        }
        app_bar.addOnOffsetChangedListener(mAppBarStateChangeListener)
    }

    private fun translationView(offset: Float) {

    }

    private fun resetPoints(isTextChanged: Boolean) {

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus) {
            return
        }
        resetPoints(false)
    }
}