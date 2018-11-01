package com.torres.valmir.kotlinMvpDagger2.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import com.torres.valmir.kotlinMvpDagger2.R

open class AppRater (mContext: Context) {
    private val APP_TITLE = "PopCorn"// App Name
    private val APP_PNAME = "com.torres.valmir.kotlinMvpDagger2"// Package Name

    private val DAYS_UNTIL_PROMPT = 3//Min number of days
    private val LAUNCHES_UNTIL_PROMPT = 3//Min number of launches

    init {
        val prefs = mContext.getSharedPreferences("apprater", 0)
        if (!prefs.getBoolean("dontshowagain", false)) {
            val editor = prefs.edit()

            // Increment launch counter
            val launch_count = prefs.getLong("launch_count", 0) + 1
            editor.putLong("launch_count", launch_count)

            // Get date of first launch
            var date_firstLaunch: Long? = prefs.getLong("date_firstlaunch", 0)
            if (date_firstLaunch == 0L) {
                date_firstLaunch = System.currentTimeMillis()
                editor.putLong("date_firstlaunch", date_firstLaunch)
            }

            // Wait at least n days before opening
            if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
                if (date_firstLaunch != null) {
                    if (System.currentTimeMillis() >= date_firstLaunch +
                            (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
                        showRateDialog(mContext, editor);
                    }
                }
            }

            editor.apply()
        }
    }

    private fun showRateDialog(mContext: Context, editor: SharedPreferences.Editor?) {
        val builder = android.support.v7.app.AlertDialog.Builder(mContext)
        val temp3 = mContext.getString(R.string.rate_it) + " " + APP_TITLE
        val temp2 = mContext.getString(R.string.text_rate_1) + " " + APP_TITLE + mContext.getString(R.string.text_rate_2)+"\n\n"
        builder.setTitle(temp3)
        builder.setMessage(temp2)
        builder.setPositiveButton(mContext.getString(R.string.rate_it_now)) {_, _ ->
            mContext.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$APP_PNAME")))
            if (editor != null) {
                editor.putBoolean("dontshowagain", true)
                editor.commit()
            }
        }
        builder.setNeutralButton(mContext.getString(R.string.remind_me)) {_, _ ->
            if (editor != null) {
                editor.putLong("launch_count", 0)
                editor.commit()
            }
        }
        builder.setNegativeButton(mContext.getString(R.string.no_thanks)){_,_ ->
            if (editor != null) {
                editor.putBoolean("dontshowagain", true)
                editor.commit()
            }
        }
        builder.create()
        builder.show()
    }
}
