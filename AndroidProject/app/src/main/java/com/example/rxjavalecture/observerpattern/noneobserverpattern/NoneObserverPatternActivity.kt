package com.example.rxjavalecture.observerpattern.noneobserverpattern

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavalecture.R
import com.example.rxjavalecture.TAG_OBSERVER_PATTERN
import com.example.rxjavalecture.util.toInt

class NoneObserverPatternActivity : AppCompatActivity() {

    private lateinit var etPercent: EditText
    private lateinit var sbPercent: SeekBar
    private lateinit var tvPercent: TextView
    private lateinit var imgOpacityResult: ImageView

    private var disableSeekBarChangeListener = false
    private var disableEditTextChangeListener = false

    private val seekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (!disableSeekBarChangeListener) {
                Log.d(TAG_OBSERVER_PATTERN, "progress = $progress in SeekBar")
                updateUIBySeekBar(progress)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            if (!disableEditTextChangeListener) {
                val percent = s.toInt()
                if (isItProperRangeNumber(percent)) {
                    Log.d(TAG_OBSERVER_PATTERN, "percent = $percent in EditText")
                    updateUIByEditText(percent)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_observer_pattern)

        etPercent = findViewById(R.id.et_percent)
        sbPercent = findViewById(R.id.sb_percent)
        tvPercent = findViewById(R.id.tv_percent)
        imgOpacityResult = findViewById(R.id.img_opacity_result)

        sbPercent.setOnSeekBarChangeListener(seekBarChangeListener)
        etPercent.addTextChangedListener(textWatcher)
    }

    private fun isItProperRangeNumber(inputNumber: Int): Boolean {
        return inputNumber in 0..100
    }

    private fun updateUIByEditText(percent: Int) {
        disableSeekBarChangeListener = true
        sbPercent.progress = percent
        disableSeekBarChangeListener = false
        updateOpacityUI(percent)
    }

    private fun updateUIBySeekBar(percent: Int) {
        disableEditTextChangeListener = true
        etPercent.setText(percent.toString())
        disableEditTextChangeListener = false
        updateOpacityUI(percent)
    }

    private fun updateOpacityUI(percent: Int) {
        tvPercent.text =
            String.format(getString(R.string.first_week_observer_pattern_result_format), percent)
        imgOpacityResult.alpha = percent / 100f
    }
}