package com.example.rxjavalecture.observerpattern.oberserverpattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentEditText
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentImageView
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentSeekBar
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentTextView
import com.example.rxkotlin.R

class ObserverPatternActivity : AppCompatActivity() {

    private lateinit var etPercent: PercentEditText
    private lateinit var sbPercent: PercentSeekBar
    private lateinit var tvPercent: PercentTextView
    private lateinit var imgOpacityResult: PercentImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observer_pattern)

        etPercent = findViewById(R.id.et_percent)
        sbPercent = findViewById(R.id.sb_percent)
        tvPercent = findViewById(R.id.tv_percent)
        imgOpacityResult = findViewById(R.id.img_opacity_result)
    }

    companion object {
        val progressSubject = RxKotlinSubject<Int>()
    }
}