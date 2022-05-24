package com.lcw.study.rxstudy.step8

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lcw.study.rxstudy.R
import com.lcw.study.rxstudy.databinding.ActivityOperatorSecondTestBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

// 1. map / flatMap / switchMap 등 수업때 다루었던 데이터 흐름제어 operator를 어떨 때 사용하면 좋은지 각 Operator 별로 생각 해와서 공유 해 주세요.
// 1. map / flatMap / switchMap 등을 활용하여 데이터를 변환, 필터링 해 보고, 어떻게 동작하는지 확인 해 주세요. 수업때 보았던 예시코드들을 활용해도 좋고, 직접 만들어 보셔도 좋습니다
// 1. Debounce / Throttle ( sample ) 도 무엇인지 공부해오고 예시 코드 작성하기

class OperatorSecondTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOperatorSecondTestBinding
    private val compositeDisposable = CompositeDisposable()
    private val TAG = "OPERATOR"

    private val emittedIntegerList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2)
    private val fromIterableSource = Observable.fromIterable(emittedIntegerList)

    private var startedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_operator_second_test)
        runMapExample()
        runMapExample2()
        flatMapTest()
    }

    private fun runMapExample() {
        compositeDisposable
            .add(
                fromIterableSource
                    .map { "emitted integer -> $it" }
                    .subscribe(::simpleLog)
            )
    }

    private fun runMapExample2() {
        compositeDisposable
            .add(
                fromIterableSource
                    .map { "emitted2 integer -> ${it+1}" }
                    .subscribe(::simpleLog)
            )
    }

    private fun flatMapTest() {
        val getDoubleDiamonds = { number: Int ->
            Observable.just("${number}🔶")
        }

        compositeDisposable.add(
            fromIterableSource
                .flatMap(getDoubleDiamonds)
                .subscribeOn(Schedulers.io())
                .subscribe{ str ->
                    println(str)
                }
        )


    }


    private fun simpleLog(message:String){
        Log.d(TAG,message)
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}