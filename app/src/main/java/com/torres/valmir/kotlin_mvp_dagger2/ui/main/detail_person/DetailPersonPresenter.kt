package com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail_person

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.HomeActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import javax.inject.Inject

class DetailPersonPresenter: DetailPersonContract.Presenter {

    @Inject
    lateinit var context: Context

    private lateinit var view: DetailPersonContract.View

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: DetailPersonContract.View) {
        this.view = view
    }

    override fun sharePerson(id: Int, type: String) {
        val message = context.getString(R.string.share_message) +
                " " +
                Constants.SHARE_URL +
                type +
                "/" +
                id
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.type = "text/plain"

        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share)+ " " + type))
    }

    override fun sendToHomeActivity(activity: AppCompatActivity) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}