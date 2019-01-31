package com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings

import android.content.Context
import android.content.SharedPreferences
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.LANGUAGE
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.LANGUAGE_TYPES
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.VISIBLE_LANGUAGE

class SettingsPresenter: SettingsContract.Presenter {

    private lateinit var view: SettingsContract.View
    private lateinit var preferences: SharedPreferences

    override fun attach(view: SettingsContract.View) {
        this.view = view
    }

    override fun editPreferencesLanguage(activity: BaseActivity, language: String, code: String) {
        preferences = activity.getSharedPreferences(LANGUAGE_TYPES, Context.MODE_PRIVATE)

        view.setLanguage(language)
        val editor = preferences.edit()
        editor.putString(VISIBLE_LANGUAGE, language)
        editor.putString(LANGUAGE, code)
        editor.apply()
    }

    override fun getPreferences(activity: BaseActivity) {
        preferences = activity.getSharedPreferences(LANGUAGE_TYPES, Context.MODE_PRIVATE)

        val preference = preferences.getString(VISIBLE_LANGUAGE, "English")
        view.setLanguage(preference)
    }
}