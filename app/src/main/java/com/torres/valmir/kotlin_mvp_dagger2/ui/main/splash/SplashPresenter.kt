package com.torres.valmir.kotlin_mvp_dagger2.ui.main.splash

import android.content.Intent
import android.os.Handler
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeActivity

class SplashPresenter: SplashContract.Presenter {

    private lateinit var view: SplashContract.View

    override fun attach(view: SplashContract.View) {
        this.view = view
    }

    override fun setActivityHome(activity: BaseActivity) {
        val r = Runnable {
            val intent = Intent(activity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            activity.startActivity(intent)
            activity.finish()
        }
        Handler().postDelayed(r, 1000)
    }
}