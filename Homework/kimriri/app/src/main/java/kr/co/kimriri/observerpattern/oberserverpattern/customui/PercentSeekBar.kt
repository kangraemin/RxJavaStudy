package kr.co.kimriri.observerpattern.oberserverpattern.customui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatSeekBar
import kr.co.kimriri.observerpattern.oberserverpattern.ObserverPatternActivity
import kr.co.kimriri.observerpattern.oberserverpattern.ObserverPatternActivity.Companion.progressSubject
import kr.co.kimriri.observerpattern.oberserverpattern.ririObserver
import kr.co.kimriri.util.TAG_OBSERVER_PATTERN

class PercentSeekBar: AppCompatSeekBar, ririObserver<Int> {


    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var disableEditTextChangeListener = false

    init {
        progressSubject.subscribe(this)
        setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (!disableEditTextChangeListener) {
                    Log.e(TAG_OBSERVER_PATTERN, "progress = $progress in SeekBar")
                    progressSubject.value = progress

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }


        })

    }

    override fun notifyDataIsArrived(value: Int) {
        value.let {
            Log.e(TAG_OBSERVER_PATTERN, "value = $value in SeekBar")
            disableEditTextChangeListener = true
            progress = it
            disableEditTextChangeListener = false
        }
    }

}