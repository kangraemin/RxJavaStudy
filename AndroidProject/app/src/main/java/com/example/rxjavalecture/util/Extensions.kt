package com.example.rxjavalecture.util

import android.text.Editable

fun Editable?.toInt(): Int {
    if (isNullOrEmpty()) {
        return 0
    }
    return toString().toInt()
}