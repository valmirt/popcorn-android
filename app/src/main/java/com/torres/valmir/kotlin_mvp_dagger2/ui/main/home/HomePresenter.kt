package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings.SettingsActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.INFO
import javax.inject.Inject

class HomePresenter: HomeContract.Presenter {

    private lateinit var view: HomeContract.View
    private lateinit var preferences: SharedPreferences

    @Inject
    lateinit var context: Context

    init {
        TMDBApplication.graph.inject(this)
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun swapListFragment(activity: BaseActivity, fragment: Int) {
        val args = Bundle()
        val listFragment = ListFragment()
        args.putInt(Constants.TYPE_LIST, fragment)
        listFragment.arguments = args

        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_home, listFragment)
                .commit()
    }

    override fun setActivitySettings(activity: BaseActivity) {
        activity.startActivity(Intent(activity, SettingsActivity::class.java))
    }

    override fun setActivityAbout(activity: BaseActivity) {
        activity.startActivity(Intent(activity, AboutActivity::class.java))
    }

    override fun getPreference(activity: BaseActivity) {
        preferences = activity.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)

        val preference = preferences.getBoolean(INFO, true)
        view.showWelcomeMessage(preference)
    }

    override fun setPreference(activity: BaseActivity) {
        preferences = activity.getSharedPreferences(Constants.LANGUAGE_TYPES, Context.MODE_PRIVATE)

        val editor = preferences.edit()
        editor.putBoolean(INFO, false)
        editor.apply()
    }
}