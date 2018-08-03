package com.torres.valmir.kotlinMvpDagger2.ui.main.settings

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.torres.valmir.kotlinMvpDagger2.R
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.ENGLISH_LANGUAGE
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.LANGUAGE
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.LANGUAGE_TYPES
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.PORTUGUESE_BR_LANGUAGE
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.PORTUGUESE_PT_LANGUAGE
import com.torres.valmir.kotlinMvpDagger2.util.Constants.Companion.VISIBLE_LANGUAGE
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    private val languages = arrayOf(
            "English",
            "Português (Brasil)",
            "Português (Portugal)")

    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        preferences = getSharedPreferences(LANGUAGE_TYPES, Context.MODE_PRIVATE)

        language_settings.text = preferences.getString(VISIBLE_LANGUAGE, languages[0])

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
                    language_settings.text = languages[item]
                    val editor = preferences.edit()
                    editor.putString(VISIBLE_LANGUAGE, languages[item])
                    editor.putString(LANGUAGE, ENGLISH_LANGUAGE)
                    editor.apply()

                    dialog.dismiss()
                }
                1 -> {
                    language_settings.text = languages[item]
                    val editor = preferences.edit()
                    editor.putString(VISIBLE_LANGUAGE, languages[item])
                    editor.putString(LANGUAGE, PORTUGUESE_BR_LANGUAGE)
                    editor.apply()

                    dialog.dismiss()
                }
                2 -> {
                    language_settings.text = languages[item]
                    val editor = preferences.edit()
                    editor.putString(VISIBLE_LANGUAGE, languages[item])
                    editor.putString(LANGUAGE, PORTUGUESE_PT_LANGUAGE)
                    editor.apply()

                    dialog.dismiss()
                }
            }
        }
        builder.create()
        builder.show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
