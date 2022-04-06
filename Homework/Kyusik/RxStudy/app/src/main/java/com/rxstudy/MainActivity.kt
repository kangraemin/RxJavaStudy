package com.rxstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rxstudy.databinding.ActivityMainBinding
import com.rxstudy.operator.CombiningExampleActivity
import com.rxstudy.operator.TransformOperatorExampleActivity
import com.rxstudy.operator.CreateOperatorExampleActivity

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btObserverPattern.setOnClickListener {
            this.startActivity(Intent(this, ObserverPatternActivity::class.java))
        }

        binding.btStream.setOnClickListener {
            this.startActivity(Intent(this, StreamExampleActivity::class.java))
        }

        binding.btSubject.setOnClickListener {
            this.startActivity(Intent(this, SubjectExampleActivity::class.java))
        }

        binding.btCreateOperator.setOnClickListener {
            this.startActivity(Intent(this, CreateOperatorExampleActivity::class.java))
        }

        binding.btTransformOperator.setOnClickListener {
            this.startActivity(Intent(this, TransformOperatorExampleActivity::class.java))
        }

        binding.btCombineOperator.setOnClickListener {
            this.startActivity(Intent(this, CombiningExampleActivity::class.java))
        }

        binding.btRxBinding.setOnClickListener {
            this.startActivity(Intent(this, RxBindingExampleActivity::class.java))
        }
    }
}