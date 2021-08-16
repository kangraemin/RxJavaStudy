package com.example.rxjavalecture

import android.app.Activity
import androidx.annotation.StringRes
import com.example.rxjavalecture.introduction.nonreactive.NonReactiveExampleActivity
import com.example.rxjavalecture.introduction.reactive.ReactiveExampleActivity
import com.example.rxjavalecture.observerpattern.noneobserverpattern.NoneObserverPatternActivity
import com.example.rxjavalecture.observerpattern.oberserverpattern.ObserverPatternActivity

enum class ExampleItem(val destinationClass: Class<out Activity>, @StringRes val exampleTitleRes: Int) {
    NONE_OBSERVER_PATTERN(NoneObserverPatternActivity::class.java, R.string.first_week_observer_pattern_example_first),
    OBSERVER_PATTERN(ObserverPatternActivity::class.java, R.string.first_week_observer_pattern_example_second),
    NONE_REACTIVE_EXAMPLE(NonReactiveExampleActivity::class.java, R.string.first_week_reactive_programming_example_first),
    REACTIVE_EXAMPLE(ReactiveExampleActivity::class.java, R.string.first_week_reactive_programming_example_second)
}
