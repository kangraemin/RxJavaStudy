package com.rxstudy.customui

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.rxstudy.MyObserver
import com.rxstudy.ObserverPatternActivity.Companion.graphSubject
import com.rxstudy.util.getAddLinear

class GraphImageView : AppCompatImageView, MyObserver<Int> {
    private var getWidth = 0
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        graphSubject.subscribe(this)
    }

    override fun notifyDataIsArrived(value: Int?) {
        if (getWidth == 0) {
            getWidth = width / 100
        }
        val params: ViewGroup.LayoutParams = layoutParams
        value?.let {
            params.width = (getWidth * it + getAddLinear(context, it, 0.3f))
            layoutParams = params
        }
    }
}