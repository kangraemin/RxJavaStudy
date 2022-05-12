package com.example.rxstudy.subject

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class SubjectSample {
   val compositeDisposable = CompositeDisposable()

   private fun publishSubjectSample() {
      val publishSubject = PublishSubject.create<String>()

      publishSubject.onNext("1")
      publishSubject.onNext("2")

      compositeDisposable.add(
         publishSubject.subscribe(
            { data ->
               threadLog(data)
            }, { exception ->
               exception.printStackTrace()
            }
         )
      )

      publishSubject.onNext("3")

      threadLog("구분구분구분구분구분선")

      compositeDisposable.add(
         publishSubject.subscribe({ data ->
            threadLog(data)
         }, { e ->
            e.printStackTrace()
         })
      )
   }

   private fun behaviorSubjectSample() {
      val behaviorSubject = BehaviorSubject.create<String>()
      behaviorSubject.onNext("be1")
      behaviorSubject.onNext("be2")

      compositeDisposable.add(
         behaviorSubject.subscribe(
            { data ->
               threadLog(data)
            }, { e ->
               e.printStackTrace()
            })
      )

      behaviorSubject.onNext("be3")

      threadLog("-----------------------------")

      compositeDisposable.add(
         behaviorSubject.subscribe(
            { data ->
               threadLog(data)
            }, { e ->
               e.printStackTrace()
            })
      )
   }

   private fun asyncSubjectSample() {
      val asyncSubject = AsyncSubject.create<String>()

      asyncSubject.onNext("async1")
      asyncSubject.onNext("async2")

      compositeDisposable.add(
         asyncSubject
            .observeOn(Schedulers.io())
            .subscribe({ data ->
               threadLog(data)
            }, { e ->
               e.printStackTrace()
            })
      )

      asyncSubject.onNext("async3")

      threadLog("-----------구분선--------")

      compositeDisposable.add(
         asyncSubject
            .subscribe({ data -> threadLog(data) }, { e -> e.printStackTrace() })
      )

      //중요! onComplete가 안되면 어떤 데이터도 받을 수 없음
      asyncSubject.onComplete()
   }

   private fun replaySubjectSample() {
      val replaySubject = ReplaySubject.create<String>()

      replaySubject.onNext("replay1")
      replaySubject.onNext("replay2")

      compositeDisposable.add(
         replaySubject.subscribe({ data -> threadLog(data) }, { e -> e.printStackTrace() })
      )

      replaySubject.onNext("replay3")

      threadLog("9 minute line------------")

      compositeDisposable.add(
         replaySubject.subscribe({ data -> threadLog(data) }, { e -> e.printStackTrace() })
      )
   }

   private fun threadLog(message: String) {
      Log.e(TAG, "thread name = ${Thread.currentThread().name} / message = $message")
   }

   companion object {
      const val TAG = "SubjectSample"
   }
}