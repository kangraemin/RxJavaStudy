package com.example.rxkotlin.com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxkotlin.R
import io.reactivex.*
import io.reactivex.Observable.create


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
            .subscribe(
                { println("onNext $it") },
                { println("onError") },
                { println("onComplete") },
                { println("onSubscribe") })

        val observableCreate = Observable.create<String> {
            it.onNext("onNext")
            it.onError(error( "onError"))
            it.onComplete()
        }.subscribe (
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") },
            { println("onSubscribe") })

        val SingleJust = Single
            .just("SingleJust")
            .subscribe(
                { print("SingleJust") },
                { it.printStackTrace() }
            )

        val SingleCreate = create<String> {
            it.onNext("SingleCreate")
        }.subscribe(
                { print("SingleCreate") },
                { it.printStackTrace() }
            )}

    val FlowableJust= Flowable.just(11, 12, 13)
        .subscribe(
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") },
            { println("onSubscribe") })


    val MaybeJust= Maybe
        .just(11)
        .subscribe(
            { println("onNext $it") },
            { println("onError") },
            { println("onComplete") }
        )

    val CompletableJust= Completable.complete()
        .subscribe { println("onComplete") }


}