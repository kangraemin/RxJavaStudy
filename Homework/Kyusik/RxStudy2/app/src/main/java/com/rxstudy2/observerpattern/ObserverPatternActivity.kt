package com.rxstudy2.observerpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.rxstudy2.R
import com.rxstudy2.databinding.ActivityObserverPatternBinding

class ObserverPatternActivity : AppCompatActivity(), MyObserver<Int>  {
    private lateinit var binding: ActivityObserverPatternBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_observer_pattern)

        progressSubject.subscribe(this)
    }
    
    companion object {
        private const val TAG = "ObserverPatternActivity"
        val progressSubject = MySubject<Int>()
    }

    override fun notifyDataIsArrived(value: Int?) {
        value?.let {
            when(it) {
                in 1..50 -> {
                    binding.rb1.isChecked = true
                }
                in 51..100 -> {
                    binding.rb2.isChecked = true
                }
                else -> {
                    binding.rgPercent.clearCheck()
                }
            }
        }
    }
}