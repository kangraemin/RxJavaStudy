package com.lcw.study.rxstudy.step7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.databinding.ActivityOperatorTestBinding

class OperatorTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOperatorTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_operator_test)

    }
}