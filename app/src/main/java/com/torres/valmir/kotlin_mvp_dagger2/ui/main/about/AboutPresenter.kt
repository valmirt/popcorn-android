package com.torres.valmir.kotlin_mvp_dagger2.ui.main.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.GITHUB_URL
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TMDB_URL
import javax.inject.Inject

class AboutPresenter: AboutContract.Presenter {

    @Inject
    lateinit var context: Context

    private lateinit var view: AboutContract.View

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: AboutContract.View) {
        this.view = view
    }

    override fun sendToFirstLink(activity: AppCompatActivity) {
        val uri = Uri.parse(TMDB_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity.startActivity(intent)
    }

    override fun sendToSecondLink(activity: AppCompatActivity) {
        val uri = Uri.parse(GITHUB_URL)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity.startActivity(intent)
    }
}