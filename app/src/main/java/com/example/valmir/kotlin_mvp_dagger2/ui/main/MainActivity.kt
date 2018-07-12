package com.example.valmir.kotlin_mvp_dagger2.ui.main

import android.os.Bundle
import com.example.valmir.kotlin_mvp_dagger2.R
import com.example.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
