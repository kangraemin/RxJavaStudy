package com.example.rxstudy.observerPattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import androidx.core.widget.doAfterTextChanged
import com.example.rxstudy.R
import com.example.rxstudy.observerPattern.util.toInt

class ObserverActivity : AppCompatActivity(), RxObserver<Int> {

    private lateinit var etPercent: EditText
    private lateinit var sbPercent: SeekBar
    private val progressSubject = RxSubject<Int>()
    private var disableEditTextChangeListener = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observer)

        etPercent = findViewById(R.id.et_percent)
        sbPercent = findViewById(R.id.sb_percent)
        progressSubject.subscribe(this)


        etPercent.doAfterTextChanged {
            if (!disableEditTextChangeListener) {
                val percent = it.toInt()
                if (isItProperRangeNumber(percent)) {
                    progressSubject.value = percent
                }
            }
        }
        sbPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!disableEditTextChangeListener) {
                    progressSubject.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        progressSubject.dispose(this)
    }

    private fun isItProperRangeNumber(inputNumber: Int): Boolean {
        return inputNumber in 0..100
    }

    override fun notifyObserverUpdate(value: Int) {
       value.let {
           disableEditTextChangeListener = true
           sbPercent.progress = it
           etPercent.setText(value.toString())
           disableEditTextChangeListener = false
       }
    }


}