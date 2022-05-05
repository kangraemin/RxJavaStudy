package com.example.rxstudy.scheduler

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rxstudy.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.StringBuilder

class SchedulerSampleActivity : AppCompatActivity() {
   private val compositeDisposable = CompositeDisposable()
   private val logStringBuilder = StringBuilder()
   private val TAG = "SchedulerSample"

   private lateinit var button: TextView
   private lateinit var button2: TextView
   private lateinit var logDescription: TextView
   private lateinit var logDescription2: TextView

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_sample_scheduler)

      button = findViewById(R.id.tv_button)
      button2 = findViewById(R.id.tv_button2)
      logDescription = findViewById(R.id.tv_log)
      logDescription2 = findViewById(R.id.tv_log2)

      setListener()
   }

   private fun setListener() {
      button.setOnClickListener {
         val observable = Observable.just(1, 2, 3, 4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
               threadLog(item.toString())
               logDescription.text = item.toString()
            }, {
               it.printStackTrace()
            })

         compositeDisposable.add(observable)
      }

      button2.setOnClickListener {
         val observable = Observable.create<String> { emitter ->
            emitter.onNext("체리 스노우 ")
            emitter.onNext("체리 사탕 ")
            emitter.onNext("녹인 맛")
         }

         compositeDisposable.add(
            observable
               .subscribeOn(Schedulers.computation())
               .doOnNext {
                  threadLog(it)
               }
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                  { data ->
                     threadLog(data)

                     val log = convertData(data)
                     logDescription2.text = log
                  }, { t ->
                     t.printStackTrace()
                  }, {
                     threadLog("onComplete")
                  }
               )
         )
      }
   }

   private fun threadLog(message: String) {
      Log.e(TAG, "thread name = ${Thread.currentThread().name} / message = $message")
   }

   private fun convertData(data: String) = logStringBuilder.append(data)

   override fun onDestroy() {
      super.onDestroy()

      compositeDisposable.dispose()
   }
}