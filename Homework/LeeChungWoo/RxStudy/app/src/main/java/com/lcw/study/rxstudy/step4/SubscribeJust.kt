package com.lcw.study.rxstudy.step4

import io.reactivex.Observable

fun main() {
// onNext 이벤트를 받았을 때 할 행동을 정의 한 경우
    var test1 = Observable
        .just("54321")
        .subscribe { data: String ->
            println("result1: $data")
        }

// onNext, onError 이벤트를 받았을 때 할 행동을 정의 한 경우
    var test2 = Observable
        .just("54321")
        .subscribe({ data: String ->
            println("result2: $data")
        }, { throwable: Throwable ->
            throwable.printStackTrace()
        })

// onNext, onError, onComplete 이벤트를 받았을 때 할 행동을 정의 한 경우
    var test3 = Observable
        .just("54321")
        .subscribe({ data: String ->
            println("result3: $data")
        }, { throwable: Throwable ->
            throwable.printStackTrace()
        }, {
            // onComplete 이벤트가 발생 했을 때 실행 할 코드
            println("result3 onComplete")
        })
}