package com.example.valmir.kotlinMvpDagger2.ui.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.ui.base.BaseActivity
import com.example.valmir.kotlinMvpDagger2.ui.main.home.HomeActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val r = Runnable {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        Handler().postDelayed(r, 1000)
    }
}
