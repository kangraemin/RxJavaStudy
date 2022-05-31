package com.example.rxstudy.rxbinding

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxstudy.databinding.ActivityRxbindingSampleBinding
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class RxBindingSampleActivity : AppCompatActivity() {
   private val compositeDisposable = CompositeDisposable()
   private lateinit var binding: ActivityRxbindingSampleBinding

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityRxbindingSampleBinding.inflate(layoutInflater)
      val view = binding.root
      setContentView(view)

      setListener()
   }

   private fun setListener() {
      val idStream = binding.etId.textChanges()
      val pwStream = binding.etPw.textChanges()

      compositeDisposable.add(
         Observable
            .combineLatest(
               binding.etId.textChanges(),
               binding.etPw.textChanges(),
               BiFunction { id: CharSequence, pw: CharSequence ->
                  return@BiFunction id.isNotEmpty() && pw.isNotEmpty()
               }
            )
            .subscribe({ isNotEmptyInput: Boolean ->
               if (isNotEmptyInput) {
                  doActivateButton()
               } else {
                  doDisabledButton()
               }
            }, {})
      )

      compositeDisposable.add(
         binding.tvLogin
            .clicks()
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe({
               Log.i("click", "login!")
            }, { it.printStackTrace() })
      )
   }

   private fun doActivateButton() {
      binding.tvLogin.apply {
         isEnabled = true
         setBackgroundColor(Color.GREEN)
      }
   }

   private fun doDisabledButton() {
      binding.tvLogin.apply {
         isEnabled = false
         setBackgroundColor(Color.WHITE)
      }
   }
}