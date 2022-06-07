package com.example.rxkotlin.com
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rxkotlin.R


class Week4Assignment : AppCompatActivity() {

    private lateinit var mJustPrint: LinearLayout
    private lateinit var mReSet: Button
    private lateinit var mGoTo6Week: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_4_assignment)
        mJustPrint = findViewById(R.id.just_print)
        mReSet = findViewById(R.id.ReSet)
        mGoTo6Week = findViewById(R.id.go_to_6_week)
        mReSet.setOnClickListener { (setRxKotlinJust()) }
        mGoTo6Week.setOnClickListener { (setGoTo6Week()) }
        RxKotlinJust()
    }

    private fun setRxKotlinJust() {
        mJustPrint.removeAllViews()
        RxKotlinJust()
    }

    private fun setGoTo6Week() {
        val mGoTo6WeekIntent : Intent = Intent(this,Week6Assignment::class.java)
        startActivity(mGoTo6WeekIntent)
        finish()
    }

    fun AddNewTextView(strrxjust: String) {
        val start = SystemClock.uptimeMillis();
        val justTextView = TextView(this)
        justTextView.text = strrxjust
        mJustPrint.addView(justTextView)
        val end = SystemClock.uptimeMillis()
        Log.e("for using stream", (end - start).toString())
    }

    fun RxKotlinJust() {
        // onNext 이벤트를 받았을 때 할 행동을 정의 한 경우
        val rxObservable1 = io.reactivex.Observable
            .just("54321")
            .subscribe {
                AddNewTextView(it)
            }

// onNext, onError 이벤트를 받았을 때 할 행동을 정의 한 경우
        val rxObservable2 = io.reactivex.Observable
            .just("54321")
            .subscribe({
                AddNewTextView(it)
            }, { _: Throwable ->

            })

// onNext, onError, onComplete 이벤트를 받았을 때 할 행동을 정의 한 경우
        val rxObservable3 = io.reactivex.Observable
            .just("54321")
            .subscribe({
                AddNewTextView(it)
            }, { _: Throwable ->

            }, {
            })
    }
}

