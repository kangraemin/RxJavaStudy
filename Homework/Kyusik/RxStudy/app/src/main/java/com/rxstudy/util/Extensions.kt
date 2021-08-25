package com.rxstudy.util

import android.content.Context
import android.text.Editable
import android.util.TypedValue

fun Editable?.toInt(): Int {
    if (isNullOrEmpty()) {
        return 0
    }
    return toString().toInt()
}

fun getAddLinear(context: Context, percent: Int, dp: Float): Int {
    var addWidth = 0
    when (percent) {
        in 61..80 -> {
            addWidth = dpToPixel(context, dp)
        }
        in 41..60 -> {
            addWidth = dpToPixel(context, dp) * 2
        }
        in 21..40 -> {
            addWidth = dpToPixel(context, dp) * 3
        }
        in 1..20 -> {
            addWidth = dpToPixel(context, dp) * 4
        }
    }
    return addWidth
}

private fun dpToPixel(context: Context, dp: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}