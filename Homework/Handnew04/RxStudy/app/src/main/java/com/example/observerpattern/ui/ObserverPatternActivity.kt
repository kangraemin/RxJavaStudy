package com.example.observerpattern.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxstudy.R
import com.example.observerpattern.DefaultSubject

class ObserverPatternActivity : AppCompatActivity() {
   companion object {
      val subject = DefaultSubject<Int>()
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_pattern_observer)
   }

   override fun onDestroy() {
      super.onDestroy()
      subject.disposeAll()
   }
}