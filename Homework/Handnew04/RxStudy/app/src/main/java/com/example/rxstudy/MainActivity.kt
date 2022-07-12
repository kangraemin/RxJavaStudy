package com.example.rxstudy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.observerpattern.ui.ObserverPatternActivity
import com.example.rxstudy.login.ui.LoginExampleActivity
import com.example.rxstudy.operator.FlowControlOperatorSampleActivity
import com.example.rxstudy.rxbinding.RxBindingSampleActivity
import com.example.rxstudy.scheduler.SchedulerSampleActivity

class MainActivity : AppCompatActivity() {
   private lateinit var observerPatternButton: Button
   private lateinit var schedulerSampleButton: Button
   private lateinit var flowOperatorButton: Button
   private lateinit var rxBindingSampleButton: Button
   private val loginExampleButton: Button by lazy { findViewById(R.id.bt_login_example) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      observerPatternButton = findViewById(R.id.bt_observer_pattern)
      schedulerSampleButton = findViewById(R.id.bt_scheduler_sample)
      flowOperatorButton = findViewById(R.id.bt_flow_control_sample)
      rxBindingSampleButton = findViewById(R.id.bt_rxbinding_sample)

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

      flowOperatorButton.setOnClickListener {
         val intent = Intent(this@MainActivity, FlowControlOperatorSampleActivity::class.java)
         startActivity(intent)
      }

      rxBindingSampleButton.setOnClickListener {
         val intent = Intent(this@MainActivity, RxBindingSampleActivity::class.java)
         startActivity(intent)
      }

      loginExampleButton.setOnClickListener {
         val intent = Intent(this@MainActivity, LoginExampleActivity::class.java)
         startActivity(intent)
      }
   }
}