package com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings

import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseContract

interface SettingsContract {
    interface View: BaseContract.View {
        fun setLanguage(language: String?)
    }

    interface Presenter: BaseContract.Presenter<SettingsContract.View>{

        fun getPreferences(activity: BaseActivity)

        fun editPreferencesLanguage(activity: BaseActivity, language: String, code: String)
    }
}