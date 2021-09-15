package com.example.rxjavalecture.subject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivitySubjectExampleBinding
import com.example.rxjavalecture.util.TAG_SUBJECT
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class SubjectExampleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivitySubjectExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subject_example)

        binding.btnObservable.setOnClickListener {
            runObservableEventExample()
        }

        binding.btnPublishSubject.setOnClickListener {
            runPublishSubjectEventExample()
        }

        binding.btnBehaviorSubject.setOnClickListener {
            runBehaviorSubjectEventExample()
        }

        binding.btnAsyncSubject.setOnClickListener {
            runAsyncSubjectEventExample()
        }

        binding.btnReplaySubject.setOnClickListener {
            runReplaySubjectEventExample()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun threadLog(message: String) {
        Log.d(TAG_SUBJECT, "thread name = ${Thread.currentThread().name} / message = $message")
    }

    private fun startTaskToGetFirstString(): String = "1".also { threadLog("Start task to emit $it") }
    private fun startTaskToGetSecondString(): String = "2".also { threadLog("Start task to emit $it") }
    private fun startTaskToGetThirdString(): String = "3".also { threadLog("Start task to emit $it") }

    private fun runObservableEventExample() {
        /*var b: Emitter<String>? = null
        val a = object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                b = emitter
            }
        }
        val observable = Observable.create(a)

        b?.onNext("1")
        b?.onNext("2")
        b?.onNext("3")

        observable
            .subscribe({
                threadLog("it = $it in first")
            }, { it.printStackTrace() })

        b?.onNext("4")
        b?.onNext("5")

        observable
            .subscribe({
                threadLog("it = $it")
            }, { it.printStackTrace() })*/

        val observablebbb = Observable.create(ObservableOnSubscribe<String> { emitter ->
            emitter.onNext("1")
            emitter.onNext("2")
            emitter.onNext("3")
            emitter.onNext("4")
            emitter.onNext("5")
        })

        observablebbb
            .subscribe({
                threadLog("it = $it in first")
            }, { it.printStackTrace() })

        observablebbb
            .subscribe({
                threadLog("it = $it")
            }, { it.printStackTrace() })


        /*observable
            .subscribe({
                threadLog("haha $it")
            }, { it.printStackTrace() })*/
    }

    private fun runPublishSubjectEventExample() {

        val publishSubject = PublishSubject.create<String>()

        publishSubject.subscribe({
            threadLog("it was subscribe in publish subject $it")
        }, { it.printStackTrace() })

        Observable
            .just(
                startTaskToGetFirstString(),
                startTaskToGetSecondString(),
                startTaskToGetThirdString()
            )
            .subscribe(publishSubject)

        // publishSubject.onNext(startTaskToGetFirstString())

        /*compositeDisposable.add(
            publishSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        publishSubject.onNext(startTaskToGetSecondString())
        publishSubject.onNext(startTaskToGetThirdString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            publishSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )*/
    }

    private fun runBehaviorSubjectEventExample() {
       /* val behaviorSubject = BehaviorSubject.create<String>()

        behaviorSubject.onNext(startTaskToGetFirstString())
        behaviorSubject.onNext(startTaskToGetSecondString())
        compositeDisposable.add(
            behaviorSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        behaviorSubject.onNext(startTaskToGetThirdString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            behaviorSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )*/

        val behaviorSubject = BehaviorSubject.createDefault("default")

        compositeDisposable.add(
            behaviorSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        behaviorSubject.onNext(startTaskToGetFirstString())
        behaviorSubject.onNext(startTaskToGetSecondString())
        behaviorSubject.onNext(startTaskToGetThirdString())

        threadLog("--------구분선--------")

        behaviorSubject.onComplete()

        compositeDisposable.add(
            behaviorSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )
    }

    private fun runAsyncSubjectEventExample() {
        val asyncSubject = AsyncSubject.create<String>()

        asyncSubject.onNext(startTaskToGetFirstString())
        asyncSubject.onNext(startTaskToGetSecondString())
        compositeDisposable.add(
            asyncSubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        asyncSubject.onNext(startTaskToGetThirdString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            asyncSubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )
        asyncSubject.onComplete()
    }

    private fun runReplaySubjectEventExample() {
        val replaySubject = ReplaySubject.createWithSize<String>(2)

        replaySubject.onNext(startTaskToGetFirstString())
        replaySubject.onNext(startTaskToGetSecondString())
        compositeDisposable.add(
            replaySubject
                .subscribe({
                    threadLog("$it in first subscribe")
                }, { it.printStackTrace() })
        )
        replaySubject.onNext(startTaskToGetThirdString())

        threadLog("--------구분선--------")

        compositeDisposable.add(
            replaySubject
                .subscribe({
                    threadLog("$it in second subscribe")
                }, { it.printStackTrace() })
        )
    }
}