package kr.co.kimriri.observerpattern.oberserverpattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.kimriri.observerpattern.oberserverpattern.customui.PercentEditText
import kr.co.kimriri.observerpattern.oberserverpattern.customui.PercentImageView
import kr.co.kimriri.observerpattern.oberserverpattern.customui.PercentSeekBar
import kr.co.kimriri.observerpattern.oberserverpattern.customui.PercentTextView

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
        val progressSubject = ririSubject<Int>()
    }
}