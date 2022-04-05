package com.example.rxstudy.observerpattern.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.rxstudy.observerpattern.DefaultObserver

class PercentTextView : AppCompatTextView, DefaultObserver<Int> {
   constructor(context: Context) : super(context)
   constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
   constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
      context,
      attrs,
      defStyleAttr
   )

   init {
      ObserverPatternActivity.subject.subscribe(this@PercentTextView)
   }

   @SuppressLint("SetTextI18n")
   override fun notifyDataIsArrived(value: Int) {
      text = "투명도: $value"
   }
}