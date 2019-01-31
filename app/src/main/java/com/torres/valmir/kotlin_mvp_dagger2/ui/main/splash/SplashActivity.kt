package com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash

import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    @Inject
    lateinit var mPresenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenter.attach(this)
        val w = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        mPresenter.setActivityHome(this)
    }
}
