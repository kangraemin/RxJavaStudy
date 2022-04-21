package com.rxstudy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rxstudy2.databinding.ActivityMainBinding
import com.rxstudy2.observerpattern.ObserverPatternActivity

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btObserverPattern.setOnClickListener {
            this.startActivity(Intent(this, ObserverPatternActivity::class.java))
        }
    }
}