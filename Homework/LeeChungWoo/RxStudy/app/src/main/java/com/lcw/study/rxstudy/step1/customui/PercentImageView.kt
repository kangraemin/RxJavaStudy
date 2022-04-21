package com.lcw.study.rxstudy.step1.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.lcw.study.rxstudy.step1.ChungObserver
import com.lcw.study.rxstudy.step1.ObserverPatternActivity

class PercentImageView : AppCompatImageView, ChungObserver<Int> {

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