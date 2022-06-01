package com.rxstudy2.observerpattern.customui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import com.rxstudy2.observerpattern.MyObserver
import com.rxstudy2.observerpattern.ObserverPatternActivity.Companion.progressSubject

class PercentSeekBar : AppCompatSeekBar, MyObserver<Int> {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var disableEditTextChangeListener = false

    init {
        progressSubject.subscribe(this)
        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (!disableEditTextChangeListener) {
                    Log.d("SEEK_BAR", "progress = $progress in SeekBar")
                    progressSubject.value = progress
//                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            Log.d("SEEK_BAR", "value = $value in SeekBar")
//            disableEditTextChangeListener = true
            progress = it
//            disableEditTextChangeListener = false
        }
    }
}