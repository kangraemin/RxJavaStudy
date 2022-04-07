package com.rxstudy2.observerpattern.customui

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import com.rxstudy2.observerpattern.MyObserver
import com.rxstudy2.observerpattern.ObserverPatternActivity.Companion.progressSubject

class PercentEditText : AppCompatEditText, MyObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var disableEditTextChangeListener = false

    init {
        progressSubject.subscribe(this)

        doAfterTextChanged {
//            if (!disableEditTextChangeListener) {
                val percent = it.toInt()
                if (isItProperRangeNumber(percent)) {
                    Log.d("EDIT_TEXT", "percent = $percent in EditText")
                    progressSubject.value = percent
                }
//            }
        }
    }

    private fun isItProperRangeNumber(inputNumber: Int): Boolean {
        return inputNumber in 0..100
    }

    private fun Editable?.toInt(): Int {
        if (isNullOrEmpty()) {
            return 0
        }
        return toString().toInt()
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            Log.d("EDIT_TEXT", "value = $value in EditText")
//            disableEditTextChangeListener = true
            setText(value.toString())
//            disableEditTextChangeListener = false
        }
    }
}