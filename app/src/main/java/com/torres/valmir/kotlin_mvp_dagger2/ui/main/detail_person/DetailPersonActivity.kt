package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person

import android.os.Bundle
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import javax.inject.Inject

class DetailPersonActivity: BaseActivity(), DetailPersonContract.View {

    @Inject
    lateinit var mPresenter: DetailPersonContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.attach(this)
    }
}