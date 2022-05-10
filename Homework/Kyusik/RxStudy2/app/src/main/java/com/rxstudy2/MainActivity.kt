package com.rxstudy2

import android.content.Intent
import android.util.Log
import com.rxstudy2.base.BaseActivity
import com.rxstudy2.databinding.ActivityMainBinding
import com.rxstudy2.observerpattern.ObserverPatternActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun init() {
        binding.apply {
            btObserverPattern.setOnClickListener {
                startActivity(Intent(this@MainActivity, ObserverPatternActivity::class.java))
            }
            btObservableJust.setOnClickListener {
                compositeDisposable.add(
                    Observable
                        .just("aaaaa")
                        .subscribe({
                            Log.d("onNext", "just: $it")
                        }, {
                            it.printStackTrace()
                        }, {
                            Log.d("onComplete", "")
                        })
                )
            }
            btObservableInterval.setOnClickListener {
                compositeDisposable.add(
                    Observable
                        .interval(1, TimeUnit.SECONDS)
                        .take(10)
                        .map { count -> count + 1 }
                        .subscribe({
                            Log.d("onNext", "count: $it")
                        }, {
                            it.printStackTrace()
                        }, {
                            Log.d("onComplete", "")
                        })
                )
            }
        }
    }
}