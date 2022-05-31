package com.example.rxstudy.operator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxstudy.R
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


// notion https://handnew.notion.site/8-e6379fe2de4a48a090ac6c34fb997e62
class FlowControlOperatorSampleActivity : AppCompatActivity() {
   private val emittedIntegerList = listOf(5, 6, 7)
   private val fromIterableSource = Observable.fromIterable(emittedIntegerList)
   private val compositeDisposable = CompositeDisposable()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_flow_control_operator)

      //runTimerWithTake()
      //runSkipWhileExample()
      //runFilterExample()
      //runMapExample()
      //runFlatMapExample()
      runSwitchMapExample()
   }

   private fun runTakeExample() {
      compositeDisposable.add(
         fromIterableSource
            .take(3)
            .subscribe({ simpleLog("take $it") }, {})
      )
   }

   private val totalTime = 5
   private var currentRemainTime = totalTime

   private fun runTimerWithTake() {
      compositeDisposable.add(
         Observable
            .interval(1000, TimeUnit.MILLISECONDS)
//            .doOnSubscribe {
//               simpleLog("doOnSubscribe!")
//               currentRemainTime = totalTime }
            .doOnNext { simpleLog("doOnNext time : $currentRemainTime") }
            .takeWhile { currentRemainTime > 0 }
            .observeOn(Schedulers.io())
            .subscribe({
               simpleLog("emitted value = $it")
               currentRemainTime--
            }, {
               it.printStackTrace()
            }, {
               simpleLog("Timer is Done")
            })
      )
   }

   private fun runSkipWhileExample() {
      compositeDisposable.add(
         fromIterableSource
            .skipWhile { it < 3 }
            .subscribe({ simpleLog("skipWhile emitted value: $it") },
               { it.printStackTrace() }, {})
      )
   }

   private fun runFilterExample() {
      compositeDisposable.add(
         fromIterableSource
            .filter { it < 3 }
            .subscribe({ simpleLog("filter emitted value: $it") },
               { it.printStackTrace() })
      )
   }

   private fun runMapExample() {
      compositeDisposable.add(
         fromIterableSource
            .map { "map emitted integer -> $it" }
            .subscribe({ simpleLog(it) }, {})
      )
   }

   private var startedTime = 0L

   private fun startTaskToGetNameFromServer(studentNumber: Int): String =
      "receive data = $studentNumber".also { Thread.sleep(1000); simpleLog("startedTime: $startedTime // Get name ( $it ) from server") }

   private fun getNameFromServerObservable(studentNumber: Int): Observable<String> =
      Observable
         .fromCallable { startTaskToGetNameFromServer(studentNumber) }
         .subscribeOn(Schedulers.io())
         .doOnDispose { simpleLog("Task is disposed !") }


   private val fromTempClicks = Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.io())
      .takeUntil { it >= 2 }
   // .subscribeOn(Schedulers.io())

   private fun runFlatMapExample() {
      compositeDisposable.add(
         fromTempClicks
            //  .subscribeOn(AndroidSchedulers.mainThread())
            .doOnNext { startedTime = System.currentTimeMillis() }
            .flatMap {
               simpleLog("click : $it")
               fromIterableSource
            }
            .subscribeOn(AndroidSchedulers.mainThread())
            .flatMap { getNameFromServerObservable(it) }
            .subscribe({ simpleLog("startedTime: $startedTime, data = $it") }, {
               it.printStackTrace()
            })
      )
   }

   private fun runSwitchMapExample() {
      compositeDisposable.add(
         Observable
            .just("clicked!")
            //  .subscribeOn(AndroidSchedulers.mainThread())
            .doOnNext { startedTime = System.currentTimeMillis() }
            .flatMap {
               simpleLog("click : $it")
               fromIterableSource
            }
            .switchMap { getNameFromServerObservable(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ simpleLog("startedTime: $startedTime, data = $it") }, {
               it.printStackTrace()
            })
      )
   }


   private fun simpleLog(msg: String) {
      Log.i(TAG, "${Thread.currentThread()}:: $msg")
   }

   companion object {
      const val TAG = "OperatorSample::"
   }

   override fun onDestroy() {
      super.onDestroy()
      //compositeDisposable.dispose()
   }
}