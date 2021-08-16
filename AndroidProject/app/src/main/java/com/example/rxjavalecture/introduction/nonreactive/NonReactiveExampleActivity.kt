package com.example.rxjavalecture.introduction.nonreactive

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.rxjavalecture.R

class NonReactiveExampleActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    private var enteredEmailTextProperly = false
    private var enteredPasswordTextProperly = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_reactive_example)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)

        etEmail.doAfterTextChanged {
            enteredEmailTextProperly = !it.isNullOrEmpty()
            updateButtonUI(enteredPasswordTextProperly && enteredEmailTextProperly)
        }

        etPassword.doAfterTextChanged {
            enteredPasswordTextProperly = !it.isNullOrEmpty()
            updateButtonUI(enteredPasswordTextProperly && enteredEmailTextProperly)
        }
    }

    private fun updateButtonUI(enteredAllDataProperly: Boolean) {
        btnLogin.isEnabled = enteredAllDataProperly
    }
}