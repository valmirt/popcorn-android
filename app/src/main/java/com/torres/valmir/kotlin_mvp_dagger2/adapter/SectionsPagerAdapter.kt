package com.torres.valmir.kotlin_mvp_dagger2.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    private val pages: ArrayList<Fragment> = ArrayList()
    private val titles: ArrayList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    fun addPages (f: Fragment, title: String) {
        pages.add(f)
        titles.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}