package com.torres.valmir.kotlin_mvp_dagger2.ui.main.about

import android.os.Bundle
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*
import javax.inject.Inject

class AboutActivity: BaseActivity(), AboutContract.View {

    @Inject
    lateinit var mPresenter: AboutContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        mPresenter.attach(this)

        text_2.setOnClickListener {
            mPresenter.sendToFirstLink(this)
        }

        text_3.setOnClickListener {
            mPresenter.sendToSecondLink(this)
        }
    }
}