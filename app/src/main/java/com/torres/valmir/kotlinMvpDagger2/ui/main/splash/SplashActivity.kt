package com.torres.valmir.kotlinMvpDagger2.ui.main.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.getColor
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.ui.base.BaseActivity
import com.torres.valmir.kotlinMvpDagger2.ui.main.home.HomeActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val w = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }

        val r = Runnable {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        Handler().postDelayed(r, 1000)
    }
}
