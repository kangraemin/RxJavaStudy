package com.example.rxstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.rxstudy.observerpattern.ui.ObserverPatternActivity

class MainActivity : AppCompatActivity() {
   private lateinit var observerPatternButton: Button

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      observerPatternButton = findViewById(R.id.bt_observer_pattern)

      setListeners()
   }

   private fun setListeners() {
      observerPatternButton.setOnClickListener {
         val intent = Intent(this@MainActivity, ObserverPatternActivity::class.java)
         startActivity(intent)
      }
   }
}