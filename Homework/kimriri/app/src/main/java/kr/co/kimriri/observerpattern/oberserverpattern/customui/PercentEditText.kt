package kr.co.kimriri.observerpattern.oberserverpattern.customui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import kr.co.kimriri.observerpattern.oberserverpattern.ObserverPatternActivity.Companion.progressSubject
import kr.co.kimriri.observerpattern.oberserverpattern.ririObserver
import kr.co.kimriri.util.TAG_OBSERVER_PATTERN
import java.lang.NumberFormatException

class PercentEditText : AppCompatEditText, ririObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context,attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,attrs,defStyleAttr)

    private var disableEditTextChangeListener = false

    init {

        progressSubject.subscribe(this)

        doAfterTextChanged {
            if (!disableEditTextChangeListener) {
                try {
                    val percent = Integer.parseInt(it.toString())
                    if(isItProperRangeNumber(percent)) {
                        Log.e(TAG_OBSERVER_PATTERN, "percent = $percent in EditText")
                        progressSubject.value = percent
                    }
                }catch (e : NumberFormatException){
                    Toast.makeText(context, "0부터 100 사이의 숫자만 입력해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isItProperRangeNumber(inputNumber: Int) : Boolean {
        return inputNumber in 0..100
    }
        override fun notifyDataIsArrived(value: Int) {
            value.let {
                Log.e(TAG_OBSERVER_PATTERN, "value = $value in EditText")
                disableEditTextChangeListener = true
                setText(value.toString())
                disableEditTextChangeListener = false

            }
    }


}