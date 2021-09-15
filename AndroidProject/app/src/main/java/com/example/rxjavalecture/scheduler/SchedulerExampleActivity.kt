package com.example.rxjavalecture.scheduler

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivitySchedulerExampleBinding
import com.example.rxjavalecture.util.TAG_SCHEDULER
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SchedulerExampleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivitySchedulerExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scheduler_example)

        binding.btnMain.setOnClickListener {
            runMainExample()
        }

        binding.btnSubscribeOnIo.setOnClickListener {
            runSubscribeOnIoExample()
        }

        binding.btnObserveOnMainThread.setOnClickListener {
            runObserveOnMainThreadExample()
        }

        binding.btnComputation.setOnClickListener {
            runComputationExample()
        }

        binding.btnMultipleObserveOn.setOnClickListener {
            runMultipleObserveOnExample()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun threadLog(message: String) {
        Log.d(TAG_SCHEDULER, "thread name = ${Thread.currentThread().name} / message = $message")
    }

    private fun runMainExample() {
        compositeDisposable.add(
            Observable
                .just("1", "2", "3")
                .subscribe({
                    threadLog(it)
                }, { it.printStackTrace() })
        )
    }


    private fun runSubscribeOnIoExample() {
        // subscribeOn -> 이벤트를 발행 하는 곳과 / 이벤트를 구독 하는 곳의 스케쥴러를 정하는 operator
        /*Thread {
            Observable
                .just("1", "2", "3")
                .subscribe({
                    threadLog(it)
                }, { it.printStackTrace() })
        }.start()*/

//        compositeDisposable.add(
//            Observable
//                .just("1", "2", "3")
//                .doOnNext { threadLog(it) }
//                .subscribeOn(Schedulers.io())
//                .doOnNext { threadLog(it) }
//                .subscribe({
//                    threadLog(it)
//                }, { it.printStackTrace() })
//        )

        compositeDisposable.add(
            Observable
                .just("1", "2", "3")
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .subscribe({
                    threadLog(it)
                }, { it.printStackTrace() })
        )
    }

    private fun runObserveOnMainThreadExample() {
//        compositeDisposable.add(
//            Observable
//                .just("1", "2", "3")
//                .doOnNext { threadLog(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    threadLog("it in subscribe $it")
//                }, { it.printStackTrace() })
//        )

        /*compositeDisposable.add(
            Observable
                .just("4", "5", "6")
                .doOnNext { Log.d("asdf", it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 2)
                .subscribe({
                    Log.d("asdf", "it in subscribe $it")
                }, { it.printStackTrace() })
        )

        compositeDisposable.add(
            Flowable
                .just("4", "5", "6")
                .doOnNext { Log.d("asdf", "$it in flowable") }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread(), false, 2)
                .subscribe({
                    Log.d("asdf", "it in subscribe $it in flowable")
                }, { it.printStackTrace() })
        )

        compositeDisposable.add(
            Observable
                .just("1", "2", "3")
                .doOnNext { Log.d("asdf", it) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("asdf", "it in subscribe $it")
                }, { it.printStackTrace() })
        )*/
    }

    private fun runComputationExample() {
        compositeDisposable.add(
            Observable
                .just("1", "2", "3")
                .subscribeOn(Schedulers.computation())
                .doOnNext { threadLog(it) }
                .subscribe({
                    threadLog("it in subscribe $it")
                }, { it.printStackTrace() })
        )
    }

    private fun runMultipleObserveOnExample() {
        compositeDisposable.add(
            Observable
                .just("1", "2", "3")
                .subscribeOn(Schedulers.io()) // 파란색
                .doOnNext { threadLog(it) } // 파란색 ?
                .observeOn(Schedulers.computation()) // 노란색
                .doOnNext { threadLog(it) } // 노란색 ?
                .observeOn(AndroidSchedulers.mainThread()) // 빨간색
                .subscribe({
                    threadLog("it in subscribe $it") //
                }, { it.printStackTrace() })
        )

        compositeDisposable.add(
            Observable
                .just("1", "2", "3")
                .subscribeOn(Schedulers.io()) // 파란색
                .observeOn(Schedulers.computation()) // 노란색
                .doOnNext { threadLog(it) } // 노란색 ?
                .observeOn(AndroidSchedulers.mainThread()) // 빨간색
                .subscribe({
                    threadLog("it in subscribe $it") //
                }, { it.printStackTrace() })
        )

//        val observable = Observable.create<String> { emitter ->
//            emitter.onNext("1")
//            emitter.onNext("2")
//            emitter.onNext("3")
//            emitter.onNext("4")
//        }
//
//        compositeDisposable.add(
//            observable
//                .subscribeOn(Schedulers.io())
//                .doOnNext { threadLog(it) }
//                .observeOn(Schedulers.computation())
//                .doOnNext { threadLog(it) }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    threadLog("it in subscribe $it")
//                }, { it.printStackTrace() })
//        )

//        val observable = Observable.create<String> { emitter ->
//            emitter.onNext("1")
//            emitter.onNext("2")
//            emitter.onNext("3")
//            emitter.onNext("4")
//            emitter.onError(Error())
//        }
//
//        compositeDisposable.add(
//            observable
//                .subscribeOn(Schedulers.io())
//                .doOnNext { threadLog(it) }
//                .observeOn(Schedulers.computation())
//                .doOnNext { threadLog(it) }
//                .doOnError { threadLog("on Error !") }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    threadLog("it in subscribe $it")
//                }, { it.printStackTrace() })
//        )
    }
}