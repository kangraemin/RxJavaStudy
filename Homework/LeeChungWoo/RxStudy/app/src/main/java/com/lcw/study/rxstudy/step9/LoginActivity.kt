package com.lcw.study.rxstudy.step9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.databinding.ActivityLoginBinding
import io.reactivex.disposables.CompositeDisposable

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}