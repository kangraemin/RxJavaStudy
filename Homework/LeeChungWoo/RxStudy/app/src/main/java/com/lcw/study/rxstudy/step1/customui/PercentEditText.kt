package com.lcw.study.rxstudy.step1.customui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import com.lcw.study.rxstudy.step1.ChungObserver
import com.lcw.study.rxstudy.step1.ObserverPatternActivity.Companion.progressSubject
import com.lcw.study.rxstudy.util.TAG_OBSERVER_PATTERN
import com.lcw.study.rxstudy.util.toInt

class PercentEditText : AppCompatEditText, ChungObserver<Int> {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var disableEditTextChangeListener = false

    init {
        progressSubject.subscribe(this)

        doAfterTextChanged {
            if (!disableEditTextChangeListener) {
                val percent = it.toInt()
                if (isItProperRangeNumber(percent)) {
                    Log.d(TAG_OBSERVER_PATTERN, "percent = $percent in EditText")
                    progressSubject.value = percent
                }
            }
        }
    }

    private fun isItProperRangeNumber(inputNumber: Int): Boolean {
        return inputNumber in 0..100
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            Log.d(TAG_OBSERVER_PATTERN, "value = $value in EditText")
            disableEditTextChangeListener = true
            setText(value.toString())
            disableEditTextChangeListener = false
        }
    }

}