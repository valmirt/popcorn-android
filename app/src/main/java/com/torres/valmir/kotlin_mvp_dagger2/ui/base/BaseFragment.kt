package com.torres.valmir.kotlin_mvp_dagger2.ui.base

import android.support.v4.app.Fragment
import com.torres.valmir.kotlin_mvp_dagger2.TMDBApplication
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.casting.CastingFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.info.InfoFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.detail.season.SeasonFragment
import com.torres.valmir.kotlin_mvp_dagger2.ui.main.home.list.ListFragment

abstract class BaseFragment: Fragment() {

    init {
        injector()
    }

    private fun injector(){
        when (this) {
            is CastingFragment -> TMDBApplication.graph.inject(this)
            is InfoFragment -> TMDBApplication.graph.inject(this)
            is SeasonFragment -> TMDBApplication.graph.inject(this)
            is ListFragment -> TMDBApplication.graph.inject(this)
        }
    }
}