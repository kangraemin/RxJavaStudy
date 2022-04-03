package kr.co.kimriri.observerpattern.oberserverpattern.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kr.co.kimriri.observerpattern.oberserverpattern.ObserverPatternActivity.Companion.progressSubject
import kr.co.kimriri.observerpattern.oberserverpattern.R
import kr.co.kimriri.observerpattern.oberserverpattern.ririObserver

class PercentTextView : AppCompatTextView, ririObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        progressSubject.subscribe(this)
    }
    override fun notifyDataIsArrived(value: Int) {

        value.let {
            text = String.format(context.getString(R.string.first_week_observer_pattern_result_format), it)

        }



    }

}