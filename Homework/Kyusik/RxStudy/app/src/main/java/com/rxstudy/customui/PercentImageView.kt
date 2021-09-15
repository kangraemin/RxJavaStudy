package com.rxstudy.customui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.rxstudy.MainActivity
import com.rxstudy.MyObserver

class PercentImageView : AppCompatImageView, MyObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        MainActivity.progressSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            alpha = it / 100f
        }
    }
}