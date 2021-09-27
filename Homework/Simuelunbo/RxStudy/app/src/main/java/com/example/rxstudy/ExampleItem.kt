package com.example.rxstudy

import android.app.Activity
import androidx.annotation.StringRes
import com.example.rxstudy.observerPattern.ObserverActivity
import com.example.rxstudy.weekFive.RxJavaStreamActivity
import com.example.rxstudy.weekSix.SubjectExampleActivity

enum class ExampleItem(val destinationClass: Class<out Activity>, @StringRes val exampleTitleRes: Int) {
    OBSERVER_PATTERN(ObserverActivity::class.java,R.string.observer_pattern),
    RX_STREAM_EXAMPLE(RxJavaStreamActivity::class.java,R.string.observable_subscribe_scheduler),
    SUBJECT_EXAMPLE(SubjectExampleActivity::class.java,R.string.subject_example)
}