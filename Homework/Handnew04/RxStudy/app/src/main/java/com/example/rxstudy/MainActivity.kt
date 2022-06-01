package com.example.rxstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rxstudy.observerpattern.ui.ObserverPatternActivity
import com.example.rxstudy.scheduler.SchedulerSampleActivity

class MainActivity : AppCompatActivity() {
   private lateinit var observerPatternButton: Button
   private lateinit var schedulerSampleButton: Button

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      observerPatternButton = findViewById(R.id.bt_observer_pattern)
      schedulerSampleButton = findViewById(R.id.bt_scheduler_sample)

      setListeners()
   }

   private fun setListeners() {
      observerPatternButton.setOnClickListener {
         val intent = Intent(this@MainActivity, ObserverPatternActivity::class.java)
         startActivity(intent)
      }

      schedulerSampleButton.setOnClickListener {
         val intent = Intent(this@MainActivity, SchedulerSampleActivity::class.java)
         startActivity(intent)
      }
   }
}