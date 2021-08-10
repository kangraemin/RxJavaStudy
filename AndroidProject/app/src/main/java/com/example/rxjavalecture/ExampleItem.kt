package com.example.rxjavalecture

import android.app.Activity
import androidx.annotation.StringRes
import com.example.rxjavalecture.observerpattern.noneobserverpattern.NoneObserverPatternActivity
import com.example.rxjavalecture.observerpattern.oberserverpattern.ObserverPatternActivity

enum class ExampleItem(val destinationClass: Class<out Activity>, @StringRes val exampleTitleRes: Int) {
    NONE_OBSERVER_PATTERN(NoneObserverPatternActivity::class.java, R.string.first_week_observer_pattern_example_first),
    OBSERVER_PATTERN(ObserverPatternActivity::class.java, R.string.first_week_observer_pattern_example_second)
}
