package com.lcw.study.rxstudy.step5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.databinding.ActivitySchedulerTestBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SchedulerTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySchedulerTestBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scheduler_test)

        binding.btn.setOnClickListener {
            schedulerTest()
        }
    }

    private fun schedulerTest() {
        compositeDisposable.add(
            Observable
                .just("1234567")
                .subscribeOn(Schedulers.io())
                .doOnNext { threadLog("doOnNext", it) }
                .observeOn(Schedulers.single())
                .subscribe({ data: String ->
                    threadLog("subscribe", data)
                    binding.tv.text = data
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun threadLog(where: String, message: String) {
        Log.d("$where thread name = ${Thread.currentThread().name} / ", "message = $message")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }


}