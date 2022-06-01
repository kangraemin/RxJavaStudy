package com.rxstudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rxstudy.databinding.ActivitySubjectExampleBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class SubjectExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectExampleBinding

    private val compositeDisposable = CompositeDisposable()

    private fun threadLog(message: String) {
        Log.d(TAG, "thread name = ${Thread.currentThread().name} / message = $message")
    }

    private fun startTaskToGetFirstString(): String = "1".also { threadLog("Start task to emit $it") }
    private fun startTaskToGetSecondString(): String = "2".also { threadLog("Start task to emit $it") }
    private fun startTaskToGetThirdString(): String = "3".also { threadLog("Start task to emit $it") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subject_example)

        binding.btPublishSubject.setOnClickListener {
            runPublishSubjectEventExample()
        }

        binding.btBehaviorSubject.setOnClickListener {
            runBehaviorSubjectEventExample()
        }

        binding.btAsyncSubject.setOnClickListener {
            runAsyncSubjectEventExample()
        }

        binding.btReplaySubject.setOnClickListener {
            runReplaySubjectEventExample()
        }
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
        private const val TAG = "SubjectActivityExample"
    }
}