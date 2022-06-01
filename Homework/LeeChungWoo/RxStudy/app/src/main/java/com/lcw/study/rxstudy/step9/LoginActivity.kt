package com.lcw.study.rxstudy.step9

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.databinding.ActivityLoginBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewInit()
    }

    private fun viewInit() {

        compositeDisposable.add(
            Observable.combineLatest(
                binding.etId.textChanges(),
                binding.etPwd.textChanges()
            ) { id, pwd ->
                binding.btnLogin.isEnabled = id.trim().isNotEmpty() && pwd.trim().isNotEmpty()

            }.subscribe()
        )

        compositeDisposable.add(
            binding.btnLogin.clicks()
                .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe{
                    Log.d("RXBINDING","버튼클릭")
                }
        )

    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}