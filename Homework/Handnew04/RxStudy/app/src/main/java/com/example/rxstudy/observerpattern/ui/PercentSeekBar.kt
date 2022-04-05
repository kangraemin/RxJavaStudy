package com.example.rxstudy.observerpattern.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.example.rxstudy.observerpattern.DefaultObserver

class PercentSeekBar : AppCompatSeekBar, DefaultObserver<Int> {
   constructor(context: Context) : super(context)
   constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
   constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
      context,
      attrs,
      defStyleAttr
   )

   init {
      ObserverPatternActivity.subject.subscribe(this@PercentSeekBar)
      setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
         override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            ObserverPatternActivity.subject.value = progress
         }

         override fun onStartTrackingTouch(seekBar: SeekBar?) {
         }

         override fun onStopTrackingTouch(seekBar: SeekBar?) {
         }

      })
   }

   override fun notifyDataIsArrived(value: Int) {
      progress = value
   }
}