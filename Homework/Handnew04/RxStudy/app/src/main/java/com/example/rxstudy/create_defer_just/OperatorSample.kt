package com.example.rxstudy.create_defer_just

import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OperatorSample {
   //생각해보기 notion : https://handnew.notion.site/7-e68f768b33a445afa2b5e0580285bc69
   private val compositeDisposable = CompositeDisposable()
   private var startedTime = 0L

   // Thread name이 표시된 log
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

   //Create
   private fun runCreateExample() {
      compositeDisposable.add(
         Observable.create<String> { emitter ->
            emitter.onNext(startTaskToGetFirstString())
            emitter.onNext(startTaskToGetSecondString())
            emitter.onComplete()
            emitter.onNext(startTaskToGetThirdString())
         }.subscribeOn(Schedulers.io())
            .subscribe({ timeStampedLog(it) }, {})
      )
      timeStampedLog("end Task")
   }

   //Just
   private fun runJustExample() {
      compositeDisposable.add(
         Observable.just(
            startTaskToGetFirstString(),
            startTaskToGetSecondString(),
            startTaskToGetThirdString()
         ).subscribeOn(Schedulers.io())
            .subscribe({ timeStampedLog(it) }, {})
      )
      timeStampedLog("end Task")
   }

   //Defer
   private fun runDeferExample() {
      compositeDisposable.add(
         Observable.defer {
            Observable.just(
               startTaskToGetFirstString(),
               startTaskToGetSecondString(),
               startTaskToGetThirdString()
            )
         }.subscribeOn(Schedulers.io())
            .subscribe({ timeStampedLog(it) }, {})
      )
      timeStampedLog("end Task")
   }

   companion object {
      const val TAG_CREATE_OPERATOR = "TAG_CREATE_OPERATOR"
   }
}