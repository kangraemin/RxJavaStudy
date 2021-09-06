package com.rxstudy.customui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import com.rxstudy.MainActivity.Companion.graphSubject
import com.rxstudy.MyObserver
import com.rxstudy.util.TAG_OBSERVER_PATTERN
import com.rxstudy.util.toInt

class GraphEditText : AppCompatEditText, MyObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var disableEditTextChangeListener = false

    init {
        graphSubject.subscribe(this)

        doAfterTextChanged {
            if (!disableEditTextChangeListener) {
                val graph = it.toInt()
                if (isItProperRangeNumber(graph)) {
                    Log.d(TAG_OBSERVER_PATTERN, "graph = $graph in EditText")
                    graphSubject.value = graph
                }
            }
        }
    }

    private fun isItProperRangeNumber(inputNumber: Int): Boolean {
        return inputNumber in 0..100
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            Log.d(TAG_OBSERVER_PATTERN, "graph = $value in EditText")
            disableEditTextChangeListener = true
            setText(value.toString())
            disableEditTextChangeListener = false
        }
    }
}