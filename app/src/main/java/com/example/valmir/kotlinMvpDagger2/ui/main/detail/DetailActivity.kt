package com.example.valmir.kotlinMvpDagger2.ui.main.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.valmir.kotlinMvpDagger2.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
    }
}
