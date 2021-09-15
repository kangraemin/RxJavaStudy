package com.example.rxstudy.weekFive

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.rxstudy.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class RxJavaStreamActivity : AppCompatActivity() {

    private lateinit var doggoImageView: ImageView
    private lateinit var btnObservable: Button
    private lateinit var btnflowable: Button
    private lateinit var btnSingle: Button
    private lateinit var btnCompletable: Button
    private lateinit var btnMaybe: Button
    private lateinit var loadingBar: ProgressBar

    private lateinit var tvResult: TextView
    private lateinit var etInput: EditText

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava_stream)

        doggoImageView = findViewById(R.id.iv_doggo)
        btnObservable = findViewById(R.id.btn_observable)
        btnflowable = findViewById(R.id.btn_flowable)
        btnSingle = findViewById(R.id.btn_single)
        btnCompletable = findViewById(R.id.btn_completable)
        btnMaybe = findViewById(R.id.btn_maybe)
        loadingBar = findViewById(R.id.progressBar)

        tvResult = findViewById(R.id.tv_result)
        etInput = findViewById(R.id.et_input)

        btnObservable.setOnClickListener {
            if (etInput.text.toString().isNotEmpty()) {
                startObservable(etInput.text.toString())
            }
        }

        btnflowable.setOnClickListener {
            if (etInput.text.toString().isNotEmpty()) {
                startFlowable(etInput.text.toString())
            }
        }

        btnSingle.setOnClickListener {
            startSingle()
        }

        btnCompletable.setOnClickListener {
            startCompletable()
        }

        btnMaybe.setOnClickListener {
            startMaybe()
        }

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun startObservable(value: String) {
        compositeDisposable.add(
            Observable.just(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    if (doggoImageView.isVisible) hideDoggoImage()

                    showProgress()
                }
                .doOnTerminate {
                    hideProgress()
                }
                .subscribe {
                    tvResult.text = it
                }
        )
    }

    private fun startFlowable(value: String) {
        compositeDisposable.add(
            Flowable.just(value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    if (doggoImageView.isVisible) hideDoggoImage()

                    showProgress()
                }
                .doOnTerminate {
                    hideProgress()
                }
                .subscribe {
                    tvResult.text = it
                }
        )
    }

    private fun startSingle() {
        compositeDisposable.add(
            Single.create<Unit> {
                it.onSuccess(Unit)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    if (doggoImageView.isVisible) {
                        hideDoggoImage()
                    } else {
                        showDoggoImage()
                    }
                },{
                    it.printStackTrace()
                })
        )
    }

    private fun startCompletable() {
        compositeDisposable.add(
            Completable.create {
                it.onComplete()
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (doggoImageView.isVisible) {
                        hideDoggoImage()
                    } else {
                        showDoggoImage()
                    }
                }
        )
    }

    private fun startMaybe() {
        Maybe
            .just("Maybe 버튼")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                toastMsg(it)
            }
    }

    private fun showProgress() {
        loadingBar.isVisible = true
    }

    private fun hideProgress() {
        loadingBar.isVisible = false
    }

    private fun showDoggoImage() {
        doggoImageView.isVisible = true
    }

    private fun hideDoggoImage() {
        doggoImageView.isVisible = false
    }

    private fun toastMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}