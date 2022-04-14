package com.example.rxstudy.observerpattern.ui

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doAfterTextChanged
import com.example.rxstudy.observerpattern.DefaultObserver

class PercentEditText : AppCompatEditText, DefaultObserver<Int> {
   constructor(context: Context) : super(context)
   constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
   constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
      context,
      attrs,
      defStyleAttr
   )

   init {
      ObserverPatternActivity.subject.subscribe(this@PercentEditText)

      doAfterTextChanged { input ->
         val percent: Int = if (input.isNullOrBlank()) {
            0
         } else {
            input.toString().toInt()
         }

         if (isCorrectPercent(percent)) {
            ObserverPatternActivity.subject.value = percent
         }
      }
   }

   private fun isCorrectPercent(input: Int): Boolean {
      return input in 0..100
   }

   override fun notifyDataIsArrived(value: Int) {
      value.toString().let {
         setText(it)
         setSelection(it.length)
      }
   }
}