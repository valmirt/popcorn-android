package com.torres.valmir.kotlinMvpDagger2.ui.main.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        text_2.setOnClickListener {
            val uri = Uri.parse("https://www.themoviedb.org/documentation/api")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        text_3.setOnClickListener {
            val uri = Uri.parse("https://github.com/valmirt/kotlin-mvp-dagger2")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
}