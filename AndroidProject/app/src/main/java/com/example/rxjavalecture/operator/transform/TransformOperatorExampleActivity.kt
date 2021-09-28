package com.example.rxjavalecture.operator.transform

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityTransformOperatorExampleBinding
import com.example.rxjavalecture.util.TAG_TRANSFORM_OPERATOR
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TransformOperatorExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransformOperatorExampleBinding

    private val compositeDisposable = CompositeDisposable()
    private var startedTime = 0L

    private val emittedIntegerList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2)
    private val fromIterableSource = Observable.fromIterable(emittedIntegerList)

    private fun startTaskToGetNameFromServer(studentNumber: Int): String =
        "Rams num = $studentNumber".also { Thread.sleep(1000); timeStampedLog("Get name ( $it ) from server") }

    private fun getNameFromServerSingle(studentNumber: Int): Single<String> =
        Single
            .fromCallable { startTaskToGetNameFromServer(studentNumber) }
            .subscribeOn(Schedulers.io())
            .doOnDispose { timeStampedLog("Task is disposed !") }

    private fun getNameFromServerObservable(studentNumber: Int): Observable<String> =
        Observable
            .fromCallable { startTaskToGetNameFromServer(studentNumber) }
            .subscribeOn(Schedulers.io())
            .doOnDispose { timeStampedLog("Task is disposed !") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transform_operator_example)
        binding.btnTake.setOnClickListener {
            runTakeExample()
        }

        binding.btnTakeTimer.setOnClickListener {
            runTimerUsingTakeExample()
        }

        binding.btnSkip.setOnClickListener {
            runSkipExample()
        }

        binding.btnFilter.setOnClickListener {
            runFilterExample()
        }

        binding.btnMap.setOnClickListener {
            runMapExample()
        }

        compositeDisposable
            .add(
                binding
                    .btnFlatmap
                    .clicks()
                    .doOnNext { startedTime = System.currentTimeMillis() }
                    .flatMap { fromIterableSource }
                    .flatMap { getNameFromServerObservable(it) }
                    // .flatMapSingle { getNameFromServerSingle(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                binding
                    .btnSwitchmap
                    .clicks()
                    .doOnNext { startedTime = System.currentTimeMillis() }
                    .flatMap { fromIterableSource }
                    .switchMap { getNameFromServerObservable(it) }
                    // .switchMapSingle { getNameFromServerSingle(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                binding
                    .btnConcatmap
                    .clicks()
                    .doOnNext { startedTime = System.currentTimeMillis() }
                    .flatMap { fromIterableSource }
                    .concatMap { getNameFromServerObservable(it) }
                    // .concatMapSingle { getNameFromServerSingle(it) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        timeStampedLog("data received ! $it")
                    }, { it.printStackTrace() })
            )
    }

    private fun timeStampedLog(message: Any) {
        Log.d(
            TAG_TRANSFORM_OPERATOR,
            "thread name = ${Thread.currentThread().name} 실행 후 흐른 시간 = ${System.currentTimeMillis() - startedTime} / message = $message"
        )
    }

    private fun simpleLog(message: Any) {
        Log.d(
            TAG_TRANSFORM_OPERATOR,
            "message = $message"
        )
    }

    private fun runTakeExample() {
        compositeDisposable
            .add(
                fromIterableSource
                    .take(3)
                    .subscribe(::simpleLog)
            )

        compositeDisposable
            .add(
                fromIterableSource
                    .takeLast(3)
                    .subscribe(::simpleLog)
            )

        compositeDisposable
            .add(
                fromIterableSource
                    .takeUntil { it > 3 }
                    .subscribe(::simpleLog)
            )

        compositeDisposable
            .add(
                fromIterableSource
                    .takeWhile { it < 3 }
                    .subscribe(::simpleLog)
            )
    }

    private val totalTime = 5
    private var currentRemainTime = totalTime

    private fun runTimerUsingTakeExample() {
        compositeDisposable
            .add(
                Observable
                    .interval(1000, TimeUnit.MILLISECONDS)
                    .doOnSubscribe { currentRemainTime = totalTime }
                    .takeWhile { currentRemainTime > 0 }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        simpleLog("emitted value = $it")
                        binding.btnTakeTimer.text = currentRemainTime.toString()
                        currentRemainTime--
                    }, { it.printStackTrace() }, {
                        simpleLog("Timer is Done !")
                        binding.btnTakeTimer.text = "Timer using take Example"
                    })
            )
    }

    private fun runSkipExample() {
        compositeDisposable
            .add(
                fromIterableSource
                    .skip(3)
                    .subscribe(::simpleLog)
            )

        compositeDisposable
            .add(
                fromIterableSource
                    .skipLast(3)
                    .subscribe(::simpleLog)
            )

        compositeDisposable
            .add(
                fromIterableSource
                    .skipWhile { it < 3 }
                    .subscribe(::simpleLog)
            )
    }

    private fun runFilterExample() {
        compositeDisposable
            .add(
                fromIterableSource
                    .filter { it > 3 }
                    .subscribe(::simpleLog)
            )
    }

    private fun runMapExample() {
        compositeDisposable
            .add(
                fromIterableSource
                    .map { "emitted integer -> $it" }
                    .subscribe(::simpleLog)
            )
    }
}