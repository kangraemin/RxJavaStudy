package com.example.rxjavalecture.observerpattern.oberserverpattern

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentEditText
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentImageView
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentSeekBar
import com.example.rxjavalecture.observerpattern.oberserverpattern.customui.PercentTextView
import com.example.rxkotlin.R
import com.example.rxkotlin.com.Week4Assignment
import java.nio.channels.InterruptedByTimeoutException

class ObserverPatternActivity : AppCompatActivity() {

    private lateinit var etPercent: PercentEditText
    private lateinit var sbPercent: PercentSeekBar
    private lateinit var tvPercent: PercentTextView
    private lateinit var imgOpacityResult: PercentImageView
    private lateinit var week4Assignment_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observer_pattern)

        etPercent = findViewById(R.id.et_percent)
        sbPercent = findViewById(R.id.sb_percent)
        tvPercent = findViewById(R.id.tv_percent)
        imgOpacityResult = findViewById(R.id.img_opacity_result)
        week4Assignment_btn = findViewById(R.id.Week4Assignment_btn)
        week4Assignment_btn.setOnClickListener{goToweek4Assignment()}


    }

    private fun goToweek4Assignment() {
        val goToweek4AssignmentIntent : Intent = Intent(this,Week4Assignment::class.java)
        startActivity(goToweek4AssignmentIntent)
        finish()
    }


    companion object {
        val progressSubject = RxKotlinSubject<Int>()
    }
}