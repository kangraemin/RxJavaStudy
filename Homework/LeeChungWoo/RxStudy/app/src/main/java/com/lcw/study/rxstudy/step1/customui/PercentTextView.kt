package com.lcw.study.rxstudy.step1.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.step1.ChungObserver
import com.lcw.study.rxstudy.step1.ObserverPatternActivity

class PercentTextView : AppCompatTextView, ChungObserver<Int> {

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