package kr.co.kimriri.observerpattern.oberserverpattern.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kr.co.kimriri.observerpattern.oberserverpattern.ObserverPatternActivity
import kr.co.kimriri.observerpattern.oberserverpattern.ObserverPatternActivity.Companion.progressSubject
import kr.co.kimriri.observerpattern.oberserverpattern.ririObserver

class PercentImageView : AppCompatImageView, ririObserver<Int> {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        progressSubject.subscribe(this)

    }

    override fun notifyDataIsArrived(value: Int) {
        value.let {
            alpha = it /100f
        }
    }

}