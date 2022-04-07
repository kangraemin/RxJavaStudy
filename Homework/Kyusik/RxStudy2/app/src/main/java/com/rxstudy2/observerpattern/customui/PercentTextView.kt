package com.rxstudy2.observerpattern.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.rxstudy2.R
import com.rxstudy2.observerpattern.MyObserver
import com.rxstudy2.observerpattern.ObserverPatternActivity.Companion.progressSubject

class PercentTextView : AppCompatTextView, MyObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        progressSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            text = String.format(context.getString(R.string.alpha_format), it)
        }
    }
}