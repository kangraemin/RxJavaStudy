package com.example.rxjavalecture.streamimplementation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityStreamImplementationExampleBinding
import com.example.rxjavalecture.util.TAG_STREAM_IMPLEMENTATION
import io.reactivex.*
import io.reactivex.disposables.CompositeDisposable

class StreamImplementationExampleActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private lateinit var binding: ActivityStreamImplementationExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_stream_implementation_example)

        binding.btnObservable.setOnClickListener {
            startObservableExample()
        }
        binding.btnFlowable.setOnClickListener {
            startFlowableExample()
        }
        binding.btnSingle.setOnClickListener {
            startSingleExample()
        }
        binding.btnCompletable.setOnClickListener {
            startCompletableExample()
        }
        binding.btnMaybe.setOnClickListener {
            startMaybeExample()
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun startObservableExample() {
        val observable = Observable.create<String> { emitter ->
            emitter.onNext("1")
            emitter.onNext("2")
            emitter.onNext("3")
            emitter.onNext("4")
            emitter.onComplete()
            emitter.onError(Error())
        }
    }

    private fun startFlowableExample() {
        val flowable = Flowable.create<String>({ emitter ->
            emitter.onNext("1")
            emitter.onNext("2")
            emitter.onNext("3")
            emitter.onComplete()
            emitter.onError(Error("에러가 났어요"))
        }, BackpressureStrategy.DROP)
        compositeDisposable.add(
            flowable
                .subscribe({
                    Log.d(TAG_STREAM_IMPLEMENTATION, "도착한 메세지 : $it")
                }, { it.printStackTrace() })
        )
    }

    private fun startSingleExample() {
        val single = Single.create<String> { emitter ->
            emitter.onSuccess("1")
            emitter.onSuccess("2")
            emitter.onSuccess("3")
            emitter.onError(Throwable("에러가 났어요"))
        }
        compositeDisposable.add(
            single
                .subscribe({
                    Log.d(TAG_STREAM_IMPLEMENTATION, "도착한 메세지 : $it")
                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun startCompletableExample() {
        val completable = Completable.create { emitter ->
            emitter.onComplete()
            emitter.onError(Error("에러가 났어요"))
        }
        compositeDisposable.add(
            completable
                .subscribe({
                    Log.d(TAG_STREAM_IMPLEMENTATION, "도착한 메세지 : ")
                }, { it.printStackTrace() })
        )
    }

    private fun startMaybeExample() {
        val maybe = Maybe.create<String> { emitter ->
            emitter.onError(Error("에러가 났어요"))
        }
        compositeDisposable.add(
            maybe
                .subscribe({
                    Log.d(TAG_STREAM_IMPLEMENTATION, "도착한 메세지 : $it")
                }, {
                    it.printStackTrace()
                }, {
                    Log.d(TAG_STREAM_IMPLEMENTATION, "asdf")
                })
        )
    }
}