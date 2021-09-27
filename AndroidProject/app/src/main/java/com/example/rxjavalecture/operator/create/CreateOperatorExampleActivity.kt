package com.example.rxjavalecture.operator.create

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityCreateOperatorExampleBinding
import com.example.rxjavalecture.util.TAG_CREATE_OPERATOR
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CreateOperatorExampleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private lateinit var binding: ActivityCreateOperatorExampleBinding

    private var startedTime = 0L

    /*val createObservable = Observable.create<String> { emitter ->
        emitter.onNext(startTaskToGetFirstString())
        emitter.onNext(startTaskToGetSecondString())
        emitter.onNext(startTaskToGetThirdString())
    }

    val justObservable = Observable.just(
        startTaskToGetFirstString(),
        startTaskToGetSecondString(),
        startTaskToGetThirdString()
    )*/

    val deferObservable = Observable.defer {
        Observable.just(
            startTaskToGetFirstString(),
            startTaskToGetSecondString(),
            startTaskToGetThirdString()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_operator_example)

        binding.btnCreate.setOnClickListener {
            runCreateExample()
        }

        binding.btnJust.setOnClickListener {
            runJustExample()
        }

        binding.btnDefer.setOnClickListener {
            runDeferExample()
        }

        binding.btnFromCallable.setOnClickListener {
            runFromCallableExample()
        }

        binding.btnIterable.setOnClickListener {
            runIterableExample()
        }

        binding.btnInterval.setOnClickListener {
            runIntervalExample()
        }

        binding.btnTimer.setOnClickListener {
            runTimerExample()
        }

        binding.btnRange.setOnClickListener {
            runRangeExample()
        }
    }

    private fun timeStampedLog(message: String) {
        Log.d(
            TAG_CREATE_OPERATOR,
            "thread name = ${Thread.currentThread().name} 실행 후 흐른 시간 = ${System.currentTimeMillis() - startedTime} / message = $message"
        )
    }

    private fun startTaskToGetFirstString(): String =
        "1".also { Thread.sleep(1000); timeStampedLog("Start task to emit $it") }

    private fun startTaskToGetSecondString(): String =
        "2".also { Thread.sleep(1000); timeStampedLog("Start task to emit $it") }

    private fun startTaskToGetThirdString(): String =
        "3".also { Thread.sleep(1000); timeStampedLog("Start task to emit $it") }

    private fun runCreateExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        // 인터페이스 / SAM ( Closure ) / 익명클래스
        Observable.create<String> { emitter ->
            emitter.onNext(startTaskToGetFirstString())
            emitter.onNext(startTaskToGetSecondString())
            emitter.onNext(startTaskToGetThirdString())
        }

        /*compositeDisposable
            .add(
                // 인터페이스 / SAM ( Closure ) / 익명클래스
                Observable.create<String> { emitter ->
                    emitter.onNext(startTaskToGetFirstString())
                    emitter.onNext(startTaskToGetSecondString())
                    emitter.onNext(startTaskToGetThirdString())
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )*/

        /*compositeDisposable
            .add(
                createObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )*/
        timeStampedLog("end task")
    }

    private fun runJustExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        /*Observable
            .defer {
                PublishSubject.create<String>().apply {
                    onNext(startTaskToGetFirstString())
                    onNext(startTaskToGetSecondString())
                    onNext(startTaskToGetThirdString())
                }
            }

        PublishSubject.create<String>().apply {
            onNext(startTaskToGetFirstString())
            onNext(startTaskToGetSecondString())
            onNext(startTaskToGetThirdString())
        }*/

        /*compositeDisposable
            .add(
                Observable
                    .just(
                        startTaskToGetFirstString(),
                        startTaskToGetSecondString(),
                        startTaskToGetThirdString()
                    )
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )*/

        /*compositeDisposable
            .add(
                justObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )*/
        timeStampedLog("end task")
    }

    private fun runDeferExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        /*Observable.defer {
            Observable.just(
                startTaskToGetFirstString(),
                startTaskToGetSecondString(),
                startTaskToGetThirdString()
            )
        }*/

        /*compositeDisposable
            .add(
                Observable.defer {
                    Observable.just(
                        startTaskToGetFirstString(),
                        startTaskToGetSecondString(),
                        startTaskToGetThirdString()
                    )
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )*/

        compositeDisposable
            .add(
                deferObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )
        timeStampedLog("end task")
    }

    private fun runFromCallableExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        Observable.fromCallable {
            startTaskToGetFirstString()
            startTaskToGetSecondString()
            startTaskToGetThirdString()
        }

        compositeDisposable
            .add(
                Observable.fromCallable {
                    startTaskToGetFirstString()
                    startTaskToGetSecondString()
                    return@fromCallable startTaskToGetThirdString()
                }
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )
        timeStampedLog("end task")
    }

    private fun runIterableExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")
        val stringList = listOf("1", "2", "3")
        val stringSet = setOf("1", "2", "3")

        compositeDisposable
            .add(
                Observable
                    .fromIterable(
                        listOf(
                            startTaskToGetFirstString(),
                            startTaskToGetSecondString(),
                            startTaskToGetThirdString()
                        )
                    )
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )

        timeStampedLog("end task")
    }

    private fun runIntervalExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        Observable
            .interval(1000, TimeUnit.MILLISECONDS)

        compositeDisposable
            .add(
                Observable
                    .interval(1000, TimeUnit.MILLISECONDS)
                    .map { it.toString() }
                    .subscribe(::timeStampedLog)
            )

        timeStampedLog("end task")
    }

    private fun runTimerExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        Observable
            .timer(1000, TimeUnit.MILLISECONDS)

        compositeDisposable
            .add(
                Observable
                    .timer(1000, TimeUnit.MILLISECONDS)
                    .map { it.toString() }
                    .subscribe(
                        ::timeStampedLog,
                        { it.printStackTrace() },
                        { timeStampedLog("on Complete !") })
            )

        timeStampedLog("end task")
    }

    private fun runRangeExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        Observable
            .range(0, 10)

        compositeDisposable
            .add(
                Observable
                    .range(0, 10)
                    .map { it.toString() }
                    .subscribe(::timeStampedLog)
            )

        timeStampedLog("end task")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}