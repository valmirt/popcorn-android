package com.torres.valmir.kotlin_mvp_dagger2.ui.main.settings

import android.os.Bundle
import android.view.MenuItem
import com.torres.valmir.kotlin_mvp_dagger2.R
import com.torres.valmir.kotlin_mvp_dagger2.ui.base.BaseActivity
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.ENGLISH_LANGUAGE
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.PORTUGUESE_BR_LANGUAGE
import com.torres.valmir.kotlin_mvp_dagger2.utils.Constants.Companion.PORTUGUESE_PT_LANGUAGE
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject

class SettingsActivity : BaseActivity(), SettingsContract.View {

    @Inject
    lateinit var mPresenter: SettingsContract.Presenter

    private val languages = arrayOf(
            "English",
            "Português (Brasil)",
            "Português (Portugal)")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mPresenter.attach(this)
        mPresenter.getPreferences(this)
        button_language.setOnClickListener {
            buildSimpleDialogMessage()
        }
    }

    private fun buildSimpleDialogMessage() {
        val builder = android.support.v7.app.AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.language_settings))
        builder.setSingleChoiceItems(languages, -1) { dialog, item ->
            when (item) {
                0 -> {
                    mPresenter.editPreferencesLanguage(this, languages[item], ENGLISH_LANGUAGE)
                    dialog.dismiss()
                }
                1 -> {
                    mPresenter.editPreferencesLanguage(this, languages[item], PORTUGUESE_BR_LANGUAGE)
                    dialog.dismiss()
                }
                2 -> {
                    mPresenter.editPreferencesLanguage(this, languages[item], PORTUGUESE_PT_LANGUAGE)
                    dialog.dismiss()
                }
            }
        }
        builder.create()
        builder.show()
    }

    override fun setLanguage(language: String?) {
        language_settings.text = language
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
