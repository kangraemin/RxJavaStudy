package com.example.rxstudy

import android.app.Activity
import androidx.annotation.StringRes
import com.example.rxstudy.observerPattern.ObserverActivity
import com.example.rxstudy.weekFive.RxJavaStreamActivity
import com.example.rxstudy.weekNine.RxBindingLoginActivity

enum class ExampleItem(val destinationClass: Class<out Activity>, @StringRes val exampleTitleRes: Int) {
    OBSERVER_PATTERN(ObserverActivity::class.java,R.string.observer_pattern),
    RX_STREAM_EXAMPLE(RxJavaStreamActivity::class.java,R.string.observable_subscribe_scheduler),
    RX_BINDING_EXAMPLE(RxBindingLoginActivity::class.java,R.string.rx_binding_example)
}