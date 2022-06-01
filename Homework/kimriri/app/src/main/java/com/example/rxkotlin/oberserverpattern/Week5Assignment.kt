package com.example.rxkotlin.com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxkotlin.R
import io.reactivex.Observable


/** 각자 플랫폼에 맞게
 * Observable/Single/Flowable/Maybe/Completable에 대해
 * create 또는 just로 Stream을 만들고,
 * 이를 구독하고,
 * Scheduler를 적용해보기 */

class Week5Assignment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_5_assignment)
        val observerJust= Observable.just(11, 12, 13)
      val  observerJustReturn = observerJust.subscribe(
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") },
            { println("onSubscribe") })

        val xSubject = Observable.create<Int>()
        xSubject.subscribe { println("첫번째 $it") }
        xSubject.onNext(1)
        Thread.sleep(1000L)
        xSubject.subscribe { println("----두번째 $it") }
        xSubject.onNext(2)
        xSubject.onNext(3)
        Thread.sleep(1000L)
        xSubject.subscribe { println("********세번째 $it") }
        xSubject.onNext(4)
        xSubject.onComplete()




    }
}