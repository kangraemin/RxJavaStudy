package com.rxstudy2

import android.content.Intent
import com.rxstudy2.base.BaseActivity
import com.rxstudy2.databinding.ActivityMainBinding
import com.rxstudy2.observerpattern.ObserverPatternActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {
        binding.apply {
            btObserverPattern.setOnClickListener {
                startActivity(Intent(this@MainActivity, ObserverPatternActivity::class.java))
            }
            btStreamExample.setOnClickListener {
                startActivity(Intent(this@MainActivity, StreamExampleActivity::class.java))
            }
        }
    }
}