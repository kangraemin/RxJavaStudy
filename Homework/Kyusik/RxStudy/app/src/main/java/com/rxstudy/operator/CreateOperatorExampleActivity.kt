package com.rxstudy.operator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rxstudy.R
import com.rxstudy.databinding.ActivityCreateOperatorExampleBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CreateOperatorExampleActivity : AppCompatActivity() {
    // Create / Just / Defer / FromCallable / Interval / Timer / Range
    private lateinit var binding: ActivityCreateOperatorExampleBinding

    private val compositeDisposable = CompositeDisposable()
    private var startedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_operator_example)

        binding.btCreate.setOnClickListener {
            runCreateExample()
        }

        binding.btJust.setOnClickListener {
            runJustExample()
        }

        binding.btDefer.setOnClickListener {
            runDeferExample()
        }

        binding.btFromCallable.setOnClickListener {
            runFromCallableExample()
        }

        binding.btIterable.setOnClickListener {
            runIterableExample()
        }

        binding.btInterval.setOnClickListener {
            runIntervalExample()
        }

        binding.btTimer.setOnClickListener {
            runTimerExample()
        }

        binding.btRange.setOnClickListener {
            runRangeExample()
        }
    }

    private fun timeStampedLog(message: String) {
        Log.d(
            TAG,
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

        val createObservable = Observable.create<String> { emitter ->
            emitter.onNext(startTaskToGetFirstString())
            emitter.onNext(startTaskToGetSecondString())
            emitter.onNext(startTaskToGetThirdString())
        }

        compositeDisposable
            .add(
                createObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )
        timeStampedLog("end task")
    }

    private fun runJustExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        val justObservable = Observable.just(
            startTaskToGetFirstString(),
            startTaskToGetSecondString(),
            startTaskToGetThirdString()
        )

        compositeDisposable
            .add(
                justObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )
        timeStampedLog("end task")
    }

    private fun runDeferExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        val deferObservable = Observable.defer {
            Observable.just(
                startTaskToGetFirstString(),
                startTaskToGetSecondString(),
                startTaskToGetThirdString()
            )
        }

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

        val fromCallableObservable = Observable.fromCallable {
            startTaskToGetFirstString()
            startTaskToGetSecondString()
            return@fromCallable startTaskToGetThirdString()
        }

        compositeDisposable
            .add(
                fromCallableObservable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )
        timeStampedLog("end task")
    }

    private fun runIterableExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")
        val fromIterable = Observable
            .fromIterable(
                setOf(
                    startTaskToGetFirstString(),
                    startTaskToGetSecondString(),
                    startTaskToGetThirdString()
                )
            )
        compositeDisposable
            .add(
                fromIterable
                    .subscribeOn(Schedulers.io())
                    .subscribe(::timeStampedLog)
            )

        timeStampedLog("end task")
    }

    private fun runIntervalExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        val interval = Observable
            .interval(0,1000, TimeUnit.MILLISECONDS)

        compositeDisposable
            .add(
                interval
                    .map { it.toString() }
                    .subscribe(::timeStampedLog)
            )

        timeStampedLog("end task")
    }

    private fun runTimerExample() {
        startedTime = System.currentTimeMillis()
        timeStampedLog("start task")

        val timer = Observable
            .timer(1000, TimeUnit.MILLISECONDS)

        compositeDisposable
            .add(
                timer
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

        val range = Observable
            .range(0, 10)

        compositeDisposable
            .add(
                range
                    .map { it.toString() }
                    .subscribe(::timeStampedLog)
            )

        timeStampedLog("end task")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        private const val TAG = "CreateOperator"
    }
}