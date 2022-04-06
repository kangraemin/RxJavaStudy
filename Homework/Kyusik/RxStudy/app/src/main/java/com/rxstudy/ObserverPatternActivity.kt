package com.rxstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rxstudy.databinding.ActivityObserverPatternBinding

class ObserverPatternActivity : AppCompatActivity() {
    private lateinit var binding: ActivityObserverPatternBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_observer_pattern)
    }

    companion object {
        private const val TAG = "MainActivity"
        val progressSubject = MySubject<Int>()
        val graphSubject = MySubject<Int>()
    }
}