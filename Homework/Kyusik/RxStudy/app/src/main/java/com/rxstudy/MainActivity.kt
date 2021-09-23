package com.rxstudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rxstudy.customui.*
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var etPercent: PercentEditText
    private lateinit var sbPercent: PercentSeekBar
    private lateinit var tvPercent: PercentTextView
    private lateinit var imgOpacityResult: PercentImageView

    private lateinit var etGraph: GraphEditText
    private lateinit var imageGraph: GraphImageView
    private lateinit var tvGraph: GraphTextView

    private val compositeDisposable = CompositeDisposable()

    companion object {
        val progressSubject = MySubject<Int>()
        val graphSubject = MySubject<Int>()
    }

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
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}