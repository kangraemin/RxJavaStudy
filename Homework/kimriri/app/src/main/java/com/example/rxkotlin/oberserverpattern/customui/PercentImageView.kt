package com.example.rxjavalecture.observerpattern.oberserverpattern.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.rxjavalecture.observerpattern.oberserverpattern.ObserverPatternActivity
import com.example.rxkotlin.oberserverpattern.RxKotlinObserver

class PercentImageView : AppCompatImageView,
    RxKotlinObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        ObserverPatternActivity.progressSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            alpha = it / 100f
        }
    }
}