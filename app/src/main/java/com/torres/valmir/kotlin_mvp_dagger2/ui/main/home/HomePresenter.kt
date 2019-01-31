package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.about.AboutActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings.SettingsActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants

class HomePresenter: HomeContract.Presenter {

    private lateinit var view: HomeContract.View

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

    override fun setActivitySettings(context: Context) {
        context.startActivity(Intent(context, SettingsActivity::class.java))
    }

    override fun setActivityAbout(context: Context) {
        context.startActivity(Intent(context, AboutActivity::class.java))
    }
}