package com.rxstudy

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rxstudy.customui.*
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class MainActivity : AppCompatActivity() {
    private lateinit var etPercent: PercentEditText
    private lateinit var sbPercent: PercentSeekBar
    private lateinit var tvPercent: PercentTextView
    private lateinit var imgOpacityResult: PercentImageView

    private lateinit var etGraph: GraphEditText
    private lateinit var imageGraph: GraphImageView
    private lateinit var tvGraph: GraphTextView

    private val compositeDisposable = CompositeDisposable()

    private lateinit var publishSubject: Button
    private lateinit var behaviorSubject: Button
    private lateinit var asyncSubject: Button
    private lateinit var replaySubject: Button

    private fun threadLog(message: String) {
        Log.d(TAG, "thread name = ${Thread.currentThread().name} / message = $message")
    }

    private fun startTaskToGetFirstString(): String = "1".also { threadLog("Start task to emit $it") }
    private fun startTaskToGetSecondString(): String = "2".also { threadLog("Start task to emit $it") }
    private fun startTaskToGetThirdString(): String = "3".also { threadLog("Start task to emit $it") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPercent = findViewById(R.id.et_percent)
        sbPercent = findViewById(R.id.sb_percent)
        tvPercent = findViewById(R.id.tv_percent)
        imgOpacityResult = findViewById(R.id.img_opacity_result)

        etGraph = findViewById(R.id.et_graph)
        imageGraph = findViewById(R.id.img_graph)
        tvGraph = findViewById(R.id.tv_graph)

        publishSubject = findViewById(R.id.bt_publish_subject)
        behaviorSubject = findViewById(R.id.bt_behavior_subject)
        asyncSubject = findViewById(R.id.bt_async_subject)
        replaySubject = findViewById(R.id.bt_replay_subject)

        publishSubject.setOnClickListener {
            runPublishSubjectEventExample()
        }

        behaviorSubject.setOnClickListener {
            runBehaviorSubjectEventExample()
        }

        asyncSubject.setOnClickListener {
            runAsyncSubjectEventExample()
        }

        replaySubject.setOnClickListener {
            runReplaySubjectEventExample()
        }

       /*compositeDisposable.add(
            Observable
                .just("Observable just")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data: String ->
                    Log.e("Observable", "onNext: $data")
                }, { throwable: Throwable ->
                    Log.e("Observable", "onError: $throwable")
                }, {
                    Log.e("Observable", "onComplete")
                })
        )
        compositeDisposable.add(
            Single
                .just("Single just")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("Single", "onSuccess $it")
                }, {
                    it.printStackTrace()
                    Log.e("Single", "onError")
                })
        )
        compositeDisposable.add(
            Flowable
                .just("Flowable just")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data: String ->
                    Log.e("Flowable", "onNext: $data")
                }, { throwable: Throwable ->
                    Log.e("Flowable", "onError: $throwable")
                }, {
                    Log.e("Flowable", "onComplete")
                })
        )
        compositeDisposable.add(
            Maybe
                .just("Maybe just")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ data: String ->
                    Log.e("Maybe", "onSuccess: $data")
                }, { throwable: Throwable ->
                    Log.e("Maybe", "onError: $throwable")
                }, {
                    Log.e("Maybe", "onComplete")
                })
        )
        compositeDisposable.add(
            Completable
                .create {
                    it.onError(Error())
                    it.onComplete()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e("Completable", "onComplete")
                }, {
                    it.printStackTrace()
                    Log.e("Completable", "onError")
                })
        )*/
    }

    private fun runPublishSubjectEventExample() {
        val publishSubject = PublishSubject.create<String>()

        compositeDisposable.add(
            publishSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        publishSubject.onNext(startTaskToGetFirstString())
        publishSubject.onNext(startTaskToGetSecondString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            publishSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )

        publishSubject.onNext(startTaskToGetThirdString())
        publishSubject.onError(Throwable("Throw Error"))
//        publishSubject.onComplete()
    }

    private fun runBehaviorSubjectEventExample() {
//        val behaviorSubject = BehaviorSubject.create<String>()
        val behaviorSubject = BehaviorSubject.createDefault("default")

        compositeDisposable.add(
            behaviorSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        behaviorSubject.onNext(startTaskToGetFirstString())
        behaviorSubject.onNext(startTaskToGetSecondString())
        behaviorSubject.onError(Throwable("Throw Error"))

        threadLog("--------구분선--------")

        compositeDisposable.add(
            behaviorSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )

        behaviorSubject.onNext(startTaskToGetThirdString())
        behaviorSubject.onComplete()
    }

    private fun runAsyncSubjectEventExample() {
        val asyncSubject = AsyncSubject.create<String>()

        compositeDisposable.add(
            asyncSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        asyncSubject.onNext(startTaskToGetFirstString())
        asyncSubject.onNext(startTaskToGetSecondString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            asyncSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )

        asyncSubject.onNext(startTaskToGetThirdString())
        asyncSubject.onError(Throwable("Throw Error"))
//        asyncSubject.onComplete()
    }

    private fun runReplaySubjectEventExample() {
        val replaySubject = ReplaySubject.createWithSize<String>(2)

        compositeDisposable.add(
            replaySubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )

        replaySubject.onNext(startTaskToGetFirstString())
        replaySubject.onNext(startTaskToGetSecondString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            replaySubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )

        replaySubject.onNext(startTaskToGetThirdString())
        replaySubject.onError(Throwable("Throw Error"))
//        replaySubject.onComplete()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        private const val TAG = "MainActivity"
        val progressSubject = MySubject<Int>()
        val graphSubject = MySubject<Int>()
    }
}