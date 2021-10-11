package com.example.rxjavalecture.operator.error

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityErrorHandlingExampleBinding
import com.example.rxjavalecture.util.TAG_TRANSFORM_OPERATOR
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class ErrorHandlingExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorHandlingExampleBinding
    private val compositeDisposable = CompositeDisposable()

    private var startedTime = 0L

    private fun startTaskToGetNameFromServer(studentNumber: Int): String =
        "Rams num = $studentNumber".also {
            timeStampedLog("Start to Get name ( $it ) from server")
            Thread.sleep(1000)
            timeStampedLog("Success to Get name ( $it ) from server")
        }

    private fun getNameFromServerSingle(studentNumber: Int): Single<String> =
        Single
            .fromCallable { startTaskToGetNameFromServer(studentNumber) }
            .subscribeOn(Schedulers.io())
            .doOnDispose { timeStampedLog("Task is disposed !") }

    private fun timeStampedLog(message: Any) {
        Log.d(
            TAG_TRANSFORM_OPERATOR,
            "thread name = ${Thread.currentThread().name} 실행 후 흐른 시간 = ${System.currentTimeMillis() - startedTime} / message = $message"
        )
    }

    private val emittedIntegerList = listOf(1, 2, 3, 4, 5)
    private val fromIterableSource =
        Observable
            .fromIterable(emittedIntegerList)
            .doOnNext { timeStampedLog("emitted value = $it") }
            .doOnNext {
                if (it == 3) {
                    timeStampedLog("throw error in $it")
                    throw CustomException
                }
            }

    private object CustomException : Throwable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_error_handling_example)

        binding
            .btnDefault
            .setOnClickListener {
                runDefaultExample()
            }

        binding
            .btnRetry
            .setOnClickListener {
                runRetryExample()
            }

        binding
            .btnRetryPredicate
            .setOnClickListener {
                runRetryPredicateExample()
            }

        binding
            .btnRetryWhen
            .setOnClickListener {
                runRetryWhenExample()
            }

        binding
            .btnOnErrorResumeNext
            .setOnClickListener {
                runOnErrorResumeNextExample()
            }

        binding
            .btnOnErrorReturn
            .setOnClickListener {
                runOnErrorReturnExample()
            }
    }

    private fun runDefaultExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable.just(Unit)
                    .doOnNext { timeStampedLog("작업을 시작합니다.") }
                    .flatMap { fromIterableSource }
                    .flatMapSingle { getNameFromServerSingle(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { timeStampedLog("Error 발생 !") }
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }

    private fun runRetryExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable.just(Unit)
                    .doOnNext { timeStampedLog("작업을 시작합니다.") }
                    .retry(3)
                    .doOnNext { timeStampedLog("작업을 시작합니다22.") }
                    .doOnNext { throw CustomException }
                    //.retry(3)
                    .flatMap { fromIterableSource }
                    .flatMapSingle { getNameFromServerSingle(it) }
                    // .retry(3)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { timeStampedLog("Error 발생 !") }
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }

    private val maxRetries = 3

    private fun runRetryPredicateExample() {
        startedTime = System.currentTimeMillis()
        var count = 0
        compositeDisposable
            .add(
                Observable.just(Unit)
                    .doOnNext { timeStampedLog("작업을 시작합니다.") }
                    .flatMap { fromIterableSource }
                    .flatMapSingle { getNameFromServerSingle(it) }
                    .retry { _: Throwable ->
                        Thread.sleep(1000)
                        return@retry count++ < maxRetries
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { timeStampedLog("Error 발생 !") }
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }

    private fun runRetryWhenExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable.just(Unit)
                    .doOnNext { timeStampedLog("작업을 시작합니다.") }
                    .flatMap { fromIterableSource }
                    .flatMapSingle { getNameFromServerSingle(it) }
                    .retryWhen { errors ->
                        val counter = AtomicInteger()
                        return@retryWhen errors
                            .flatMap {
                                if (it is CustomException && counter.getAndIncrement() < maxRetries) {
                                    return@flatMap Observable.timer(1000, TimeUnit.MILLISECONDS)
                                }
                                throw it
                            }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { timeStampedLog("Error 발생 !") }
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }

    private fun runOnErrorResumeNextExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable.just(Unit)
                    .doOnNext { timeStampedLog("작업을 시작합니다.") }
                    .flatMap { fromIterableSource }
                    .flatMapSingle { getNameFromServerSingle(it) }
                    .onErrorResumeNext(getNameFromServerSingle(1).toObservable())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { timeStampedLog("Error 발생 !") }
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }

    private fun runOnErrorReturnExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable.just(Unit)
                    .doOnNext { timeStampedLog("작업을 시작합니다.") }
                    .flatMap { fromIterableSource }
                    .flatMapSingle { getNameFromServerSingle(it) }
                    .onErrorReturn { "에러가 발생했었어요" }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { timeStampedLog("Error 발생 !") }
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }
}