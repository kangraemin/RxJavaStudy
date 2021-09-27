package com.example.rxstudy.weekSix

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxstudy.R
import com.example.rxstudy.databinding.ActivitySubjectExampleBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject

class SubjectExampleActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivitySubjectExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subject_example)

        binding.btnPublishSubject.setOnClickListener {
            publishSubjectEventExample()
        }

        binding.btnBehaviorSubject.setOnClickListener {
            behaviorSubjectEventExample()
        }

        binding.btnAsyncSubject.setOnClickListener {
            asyncSubjectEventExample()
        }

        binding.btnReplaySubject.setOnClickListener {
            replaySubjectEventExample()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun publishSubjectEventExample() {
        val publishSubject = PublishSubject.create<String>()
        publishSubject.onNext("0")
        publishSubject.onNext("1")
        compositeDisposable.add(publishSubject.subscribe { data -> Log.d("check1 :", data) })
        publishSubject.onNext("2")
        publishSubject.onNext("3")
        compositeDisposable.add(publishSubject.subscribe { data -> Log.d("check2 :", data) })
        publishSubject.onNext("4")
        publishSubject.onComplete()
    }

    private fun behaviorSubjectEventExample() {
        val behaviorSubject = BehaviorSubject.createDefault("start")
        compositeDisposable.add(behaviorSubject.subscribe { data -> Log.d("check1:", data) })
        behaviorSubject.onNext("0")
        behaviorSubject.onNext("1")
        behaviorSubject.onNext("2")
        compositeDisposable.add(behaviorSubject.subscribe { data -> Log.d("check2:", data) })
        behaviorSubject.onNext("3")
        behaviorSubject.onNext("4")
        behaviorSubject.onComplete()
    }

    private fun asyncSubjectEventExample() {
        val asyncSubject = AsyncSubject.create<String>()
        compositeDisposable.add(asyncSubject.subscribe { data -> Log.d("check1: ", data) })
        asyncSubject.onNext("0")
        asyncSubject.onNext("1")
        asyncSubject.onNext("2")
        compositeDisposable.add(asyncSubject.subscribe { data -> Log.d("check2: ", data) })
        asyncSubject.onNext("3")
        asyncSubject.onComplete()
    }

    private fun replaySubjectEventExample() {
        val replaySubject = ReplaySubject.create<String>()
        replaySubject.onNext("1")
        compositeDisposable.add(replaySubject.subscribe { data -> Log.d("check1:", data) })
        replaySubject.onNext("2")
        replaySubject.onNext("3")
        compositeDisposable.add(replaySubject.subscribe { data -> Log.d("check2:", data) })
        replaySubject.onNext("4")
        replaySubject.onComplete()
    }


}