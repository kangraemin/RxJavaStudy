package com.example.rxjavalecture.operator.combining

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityCombiningExampleBinding
import com.example.rxjavalecture.util.TAG_TRANSFORM_OPERATOR
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class CombiningExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCombiningExampleBinding
    private val compositeDisposable = CompositeDisposable()

    private var startedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_combining_example)
        binding.btnMerge.setOnClickListener {
            runMergeExample()
        }

        binding.btnConcat.setOnClickListener {
            runConcatExample()
        }

        binding.btnConcatEager.setOnClickListener {
            runConcatEagerExample()
        }

        binding.btnZip.setOnClickListener {
            runZipExample()
        }

        binding.btnCombineLatest.setOnClickListener {
            runCombineLatestExample()
        }
    }

    private val integerInterval = Observable
        .interval(1000, TimeUnit.MILLISECONDS)
        .doOnNext { timeStampedLog("integer 데이터 $it 발행 되었음 !!") }
        .takeWhile { it < 10 }

    private val stringInterval = Observable
        .interval(1500, TimeUnit.MILLISECONDS)
        .takeWhile { it < 10 }
        .doOnNext { timeStampedLog("String 데이터 $it 발행 되었음 !!") }
        .map { "$it 번쨰 String 데이터" }

    private fun runMergeExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable
                    .merge(
                        integerInterval,
                        stringInterval
                    )
                    .subscribe({
                        timeStampedLog(it)
                    }, { it.printStackTrace() })
            )
    }

    private fun runConcatExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable
                    .concat(
                        integerInterval,
                        stringInterval
                    )
                    .subscribe({
                        timeStampedLog(it)
                    }, { it.printStackTrace() })
            )
    }

    private fun runConcatEagerExample() {
        startedTime = System.currentTimeMillis()
        val observableList = listOf(integerInterval, stringInterval)
        compositeDisposable
            .add(
                Observable
                    .concatEager(observableList)
                    .subscribe({
                        timeStampedLog(it)
                    }, { it.printStackTrace() })
            )
    }

    private fun timeStampedLog(message: Any) {
        Log.d(
            TAG_TRANSFORM_OPERATOR,
            "thread name = ${Thread.currentThread().name} 실행 후 흐른 시간 = ${System.currentTimeMillis() - startedTime} / message = $message"
        )
    }

    private fun runZipExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable
                    .zip(
                        integerInterval,
                        stringInterval,
                        BiFunction { integerResult: Long, stringResult: String ->
                            return@BiFunction String.format(
                                "String 데이터는 %s 이고 / integer 데이터는 %d 번째 데이터가 발행 됨",
                                stringResult,
                                integerResult
                            )
                        }
                    )
                    .subscribe({
                        timeStampedLog(it)
                    }, { it.printStackTrace() })
            )
    }

    private fun runCombineLatestExample() {
        startedTime = System.currentTimeMillis()
        compositeDisposable
            .add(
                Observable
                    .combineLatest(
                        integerInterval,
                        stringInterval,
                        BiFunction { integerResult: Long, stringResult: String ->
                            return@BiFunction String.format(
                                "String 데이터는 %s 이고 / integer 데이터는 %d 번째 데이터가 발행 됨",
                                stringResult,
                                integerResult
                            )
                        }
                    )
                    .subscribe({
                        timeStampedLog(it)
                    }, { it.printStackTrace() })
            )
    }
}