package com.example.rxjavalecture.observerpattern.oberserverpattern.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.rxjavalecture.R
import com.example.rxjavalecture.observerpattern.oberserverpattern.RamsObserver
import com.example.rxjavalecture.observerpattern.oberserverpattern.ObserverPatternActivity

class PercentTextView : AppCompatTextView,
    RamsObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        ObserverPatternActivity.progressSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            text = String.format(context.getString(R.string.first_week_observer_pattern_result_format), it)
        }
    }
}