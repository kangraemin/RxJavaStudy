package com.example.rxjavalecture

import android.app.Activity
import androidx.annotation.StringRes
import com.example.rxjavalecture.observerpattern.NoneObserverPatternActivity

enum class ExampleItem(val destinationClass: Class<out Activity>, @StringRes val exampleTitleRes: Int) {
    NONE_OBSERVER_PATTERN(NoneObserverPatternActivity::class.java, R.string.first_week_observer_pattern_example_first)
}
