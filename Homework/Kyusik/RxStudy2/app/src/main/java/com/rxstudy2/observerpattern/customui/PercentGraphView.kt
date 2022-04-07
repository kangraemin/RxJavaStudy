package com.rxstudy2.observerpattern.customui

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.rxstudy2.observerpattern.MyObserver
import com.rxstudy2.observerpattern.ObserverPatternActivity.Companion.progressSubject
import kotlin.math.roundToInt

class PercentGraphView : AppCompatImageView, MyObserver<Int> {
    private var getWidth = 0
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        progressSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        if (getWidth == 0) {
            getWidth = width / 100
        }
        val params: ViewGroup.LayoutParams = layoutParams
        value?.let {
            params.width = (getWidth * it)
            layoutParams = params
        }
    }
}