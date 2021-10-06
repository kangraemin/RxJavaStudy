package com.rxstudy;

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rxstudy.databinding.ActivityStreamExampleBinding
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StreamExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStreamExampleBinding
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stream_example)

        binding.btObservable.setOnClickListener {
            compositeDisposable.add(
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
        }

        binding.btSingle.setOnClickListener {
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
        }

        binding.btFlowable.setOnClickListener {
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
        }

        binding.btMaybe.setOnClickListener {
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
        }

        binding.btCompletable.setOnClickListener {
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
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}