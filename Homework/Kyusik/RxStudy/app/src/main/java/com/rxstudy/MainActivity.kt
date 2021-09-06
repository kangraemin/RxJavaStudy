package com.rxstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rxstudy.customui.*

class MainActivity : AppCompatActivity() {
    private lateinit var etPercent: PercentEditText
    private lateinit var sbPercent: PercentSeekBar
    private lateinit var tvPercent: PercentTextView
    private lateinit var imgOpacityResult: PercentImageView

    private lateinit var etGraph: GraphEditText
    private lateinit var imageGraph: GraphImageView
    private lateinit var tvGraph: GraphTextView

    companion object {
        val progressSubject = MySubject<Int>()
        val graphSubject = MySubject<Int>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPercent = findViewById(R.id.et_percent)
        sbPercent = findViewById(R.id.sb_percent)
        tvPercent = findViewById(R.id.tv_percent)
        imgOpacityResult = findViewById(R.id.img_opacity_result)

        etGraph = findViewById(R.id.et_graph)
        imageGraph = findViewById(R.id.img_graph)
        tvGraph = findViewById(R.id.tv_graph)
    }
}