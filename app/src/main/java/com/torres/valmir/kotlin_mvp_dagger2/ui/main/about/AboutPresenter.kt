package com.torres.valmir.kotlin_mvp_dagger2.ui.main.about

import android.content.Context
import android.content.Intent
import android.net.Uri

class AboutPresenter: AboutContract.Presenter {

    private lateinit var view: AboutContract.View

    override fun attach(view: AboutContract.View) {
        this.view = view
    }

    override fun sendToFirstLink(context: Context) {
        val uri = Uri.parse("https://www.themoviedb.org/documentation/api")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    override fun sendToSecondLink(context: Context) {
        val uri = Uri.parse("https://github.com/valmirt/kotlin-mvp-dagger2")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}