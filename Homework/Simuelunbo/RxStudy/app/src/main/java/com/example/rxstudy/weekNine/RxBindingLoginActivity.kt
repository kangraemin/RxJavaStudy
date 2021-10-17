package com.example.rxstudy.weekNine

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxstudy.R
import com.example.rxstudy.databinding.ActivityRxbindingLoginBinding
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import java.util.concurrent.TimeUnit

class RxBindingLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRxbindingLoginBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rxbinding_login)


        compositeDisposable
            .add(
                Observable
                    .combineLatest(
                        binding.etEmail.textChanges(),
                        binding.etPw.textChanges(),
                        BiFunction { email, pw ->
                            return@BiFunction email.trim().isNotEmpty() && pw.trim().isNotEmpty()
                        }
                    )
                    .subscribe {
                        binding.btnLogin.isEnabled = it
                    }
            )

        compositeDisposable.add(
            binding.btnLogin
                .clicks()
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe{
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                }
        )
    }

}