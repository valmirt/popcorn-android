package com.torres.valmir.kotlin_mvp_dagger2.ui.main.home

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.AppRater
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.NOW_PLAYING
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.POPULAR
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.POPULAR_TV
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TODAYS_TV
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TOP_RATED
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.TOP_RATED_TV
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, HomeContract.View {

    @Inject
    lateinit var mPresenter: HomeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        AppRater(this)
        mPresenter.attach(this)
        mPresenter.getPreference(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.title = getString(R.string.popular)
        mPresenter.swapListFragment(this, POPULAR)

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //Movies
            R.id.nav_popular -> {
                supportActionBar?.title = getString(R.string.popular)
                mPresenter.swapListFragment(this, POPULAR)
            }
            R.id.nav_top_rated -> {
                supportActionBar?.title = getString(R.string.top_rated)
                mPresenter.swapListFragment(this, TOP_RATED)
            }
            R.id.nav_now_playing -> {
                supportActionBar?.title = getString(R.string.now_playing)
                mPresenter.swapListFragment(this, NOW_PLAYING)
            }
            //TV Shows
            R.id.nav_popular_tv -> {
                supportActionBar?.title = getString(R.string.popular)
                mPresenter.swapListFragment(this, POPULAR_TV)
            }
            R.id.nav_top_rated_tv -> {
                supportActionBar?.title = getString(R.string.top_rated)
                mPresenter.swapListFragment(this, TOP_RATED_TV)
            }
            R.id.nav_todays_show -> {
                supportActionBar?.title = getString(R.string.today)
                mPresenter.swapListFragment(this, TODAYS_TV)
            }
            //Others
            R.id.nav_settings -> {
                mPresenter.setActivitySettings()

            }
            R.id.nav_about -> {
                mPresenter.setActivityAbout()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showWelcomeMessage(isVisible: Boolean) {
        if (isVisible) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.welcome_title))
            builder.setMessage(getString(R.string.welcome_description))
            builder.setPositiveButton(getString(R.string.colse)) { dialog, _ ->
                mPresenter.setPreference(this)
                dialog.dismiss()
            }
            builder.create()
            builder.show()

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
