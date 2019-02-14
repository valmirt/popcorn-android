package com.torres.valmir.kotlin_mvp_dagger2.utils

import android.content.Context
import android.graphics.Paint
import android.util.DisplayMetrics

class Utils {
    companion object {
        fun getDisplayMetrics (context: Context): DisplayMetrics {
            val resources = context.resources
            return resources.displayMetrics
        }

        fun convertDpToPixel(dp: Float, context: Context): Float {
            return dp * (getDisplayMetrics(context).densityDpi / 160f)
        }

        fun convertDpToPixelSize(dp: Float, context: Context): Int {
            val pixels = convertDpToPixel(dp, context)
            val res = (pixels + 0.5f).toInt()
            return when {
                res != 0 -> res
                pixels == 0f -> 0
                pixels > 0 -> 1
                else -> -1
            }
        }

        fun getTextWidth(paint: Paint, text: String): Float {
            return paint.measureText(text)
        }
    }
}