package com.lcw.study.rxstudy.step1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.step1.customui.PercentEditText
import com.lcw.study.rxstudy.step1.customui.PercentImageView
import com.lcw.study.rxstudy.step1.customui.PercentSeekBar
import com.lcw.study.rxstudy.step1.customui.PercentTextView

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
        val progressSubject = ChungSubject<Int>()
    }
}