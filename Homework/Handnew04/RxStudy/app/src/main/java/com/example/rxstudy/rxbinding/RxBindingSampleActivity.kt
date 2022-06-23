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

//9주차 노션 https://handnew.notion.site/9-9712f5ae4c4e4a21bbcbcf17112b3220
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
               idStream,
               pwStream,
               BiFunction { id: CharSequence, pw: CharSequence ->
                  Log.e("combineLatest: ", "id: $id, pw: $pw")
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

//      compositeDisposable.add(
//         Observable
//            .zip(
//               idStream
//                  .map {
//                     Log.e("zip id", "id: $it")
//                     it.isNotBlank()
//                  },
//               pwStream
//                  .map {
//                     Log.e("zip pw", "pw: $it")
//                     it.isNotBlank()
//                  },
//               BiFunction { isNotBlankId: Boolean, isNotBlackPw: Boolean ->
//                  Log.e("zip result: ", "id: $isNotBlankId, pw: $isNotBlackPw")
//                  return@BiFunction isNotBlankId && isNotBlackPw
//               }
//            )
//            .subscribe({
//               if (it) {
//                  doActivateButton()
//               } else {
//                  doDisabledButton()
//               }
//            }, {})
//      )

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