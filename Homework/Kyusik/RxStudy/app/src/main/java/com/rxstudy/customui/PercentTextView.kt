package com.rxstudy.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.rxstudy.MyObserver
import com.rxstudy.ObserverPatternActivity.Companion.progressSubject
import com.rxstudy.R

class PercentTextView : AppCompatTextView, MyObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        progressSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            text = String.format(context.getString(R.string.first_week_observer_pattern_result_format), it)
        }
    }
}