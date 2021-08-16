package com.example.rxjavalecture.introduction.reactive

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavalecture.R
import com.example.rxjavalecture.util.TAG_REACTIVE_PROGRAMMING
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

class ReactiveExampleActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_reactive_example)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        compositeDisposable.add(
            Observable
                .combineLatest(
                    etEmail.textChanges(),
                    etPassword.textChanges(),
                    BiFunction { email: CharSequence, password: CharSequence -> return@BiFunction email.isNotEmpty() && password.isNotEmpty() }
                )
                .doOnNext { Log.d(TAG_REACTIVE_PROGRAMMING, "All data is entered = $it") }
                .subscribe({
                    btnLogin.isEnabled = it
                }, { it.printStackTrace() })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}