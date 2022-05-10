package com.rxstudy2

import android.util.Log
import com.rxstudy2.base.BaseActivity
import com.rxstudy2.databinding.ActivityStreamExampleBinding
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StreamExampleActivity :
    BaseActivity<ActivityStreamExampleBinding>(R.layout.activity_stream_example) {

    override fun init() {
        binding.apply {
            btObservable.setOnClickListener {
                compositeDisposable.add(
                    Observable
                        .just("trampoline")
                        .subscribeOn(Schedulers.trampoline())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showLog("Observable", "just: $it")
                        }, {
                            it.printStackTrace()
                            showLog("Observable", "onError : $it")
                        }, {
                            showLog("Observable", "onComplete")
                        })
                )
            }
            btObservableInterval.setOnClickListener {
                compositeDisposable.add(
                    Observable
                        .interval(1, TimeUnit.SECONDS)
                        .take(10)
                        .map { count -> count + 1 }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showLog("Observable", "count: $it")
                        }, {
                            it.printStackTrace()
                            showLog("Observable", "onError : $it")
                        }, {
                            showLog("Observable", "onComplete")
                        })
                )
            }
            btSingle.setOnClickListener {
                compositeDisposable.add(
                    Single
                        .just("Single computation")
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showLog("Single", "onSuccess $it")
                        }, {
                            it.printStackTrace()
                            showLog("Single", "onError")
                        })
                )
            }
            btFlowable.setOnClickListener {
                compositeDisposable.add(
                    Flowable
                        .just("Flowable single")
                        .subscribeOn(Schedulers.single())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data: String ->
                            showLog("Flowable", "onNext: $data")
                        }, { throwable: Throwable ->
                            showLog("Flowable", "onError: $throwable")
                        }, {
                            showLog("Flowable", "onComplete")
                        })
                )
            }
            btMaybe.setOnClickListener {
                compositeDisposable.add(
                    Maybe
                        .just("Maybe io")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ data: String ->
                            showLog("Maybe", "onSuccess: $data")
                        }, { throwable: Throwable ->
                            showLog("Maybe", "onError: $throwable")
                        }, {
                            showLog("Maybe", "onComplete")
                        })
                )
            }
            btCompletable.setOnClickListener {
                compositeDisposable.add(
                    Completable
                        .create {
                            it.onError(Error())
                            it.onComplete()
                        }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            showLog("Completable", "onComplete")
                        }, {
                            it.printStackTrace()
                            showLog("Completable", "onError")
                        })
                )
            }
        }
    }

    private fun showLog(tag: String, log: String) {
        Log.e(tag, log)
        binding.tvShowLog.text = log
    }
}