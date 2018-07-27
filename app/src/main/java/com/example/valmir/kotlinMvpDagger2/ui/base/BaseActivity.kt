package com.example.valmir.kotlinMvpDagger2.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.valmir.kotlinMvpDagger2.R
import com.example.valmir.kotlinMvpDagger2.util.Constants.Companion.TYPE_LIST

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {

    fun swapFragmentHome(fragment: Fragment, type: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val args = Bundle()

        args.putInt(TYPE_LIST, type)

        fragment.arguments = args
        ft.replace(R.id.container_home, fragment)
        ft.commit()
    }
}