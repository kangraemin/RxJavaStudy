package com.lcw.study.rxstudy.step7

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.databinding.ActivityOperatorTestBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class OperatorTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOperatorTestBinding
    private var startedTime = 0L
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_operator_test)
        buttonClickEvent()
    }

    private fun buttonClickEvent() {
        binding.btnCreate.setOnClickListener { operatorCreate() }
        binding.btnJust.setOnClickListener { operatorJust() }
        binding.btnDefer.setOnClickListener { operatorDefer() }
    }

    private fun operatorCreate() {

        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        compositeDisposable.add(Observable.create<String> { emitter ->
            emitter.onNext(startTaskToGetFirstString())
            emitter.onNext(startTaskToGetSecondString())
            emitter.onNext(startTaskToGetThirdString())

        }
            .subscribeOn(Schedulers.io())
            .subscribe(::timeStampedLog))

        timeStampedLog("end task")
    }

    private fun operatorJust() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        compositeDisposable.add(Observable.create<String> { emitter ->
            emitter.onNext(startTaskToGetFirstString())
            emitter.onNext(startTaskToGetSecondString())
            emitter.onNext(startTaskToGetThirdString())

        }
            .subscribeOn(Schedulers.io())
            .subscribe(::timeStampedLog))

        timeStampedLog("end task")
    }

    private fun operatorDefer() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        compositeDisposable.add(Observable.create<String> { emitter ->
            emitter.onNext(startTaskToGetFirstString())
            emitter.onNext(startTaskToGetSecondString())
            emitter.onNext(startTaskToGetThirdString())

        }
            .subscribeOn(Schedulers.io())
            .subscribe(::timeStampedLog))

        timeStampedLog("end task")
    }

    private fun startTaskToGetFirstString(): String =
        "1".also { Thread.sleep(1000); timeStampedLog("Start task to emit $it") }

    private fun startTaskToGetSecondString(): String =
        "2".also { Thread.sleep(1000); timeStampedLog("Start task to emit $it") }

    private fun startTaskToGetThirdString(): String =
        "3".also { Thread.sleep(1000); timeStampedLog("Start task to emit $it") }

    private fun timeStampedLog(message: String) {
        Log.d(
            "TAG_CREATE_OPERATOR",
            "thread name = ${Thread.currentThread().name} 실행 후 흐른 시간 = ${System.currentTimeMillis() - startedTime} / message = $message"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}