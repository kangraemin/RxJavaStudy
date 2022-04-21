package com.rxstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.rxstudy.databinding.ActivityRxBindingExampleBinding
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class RxBindingExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRxBindingExampleBinding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_binding_example)

        var clickedCount = 0

        compositeDisposable
            .add(
                binding
                    .btnBindingClick
                    .clicks()
                    .subscribe({
                        binding.tvClickTime.text = String.format("클릭된 횟수 : %d", ++clickedCount)
                    }, { it.printStackTrace() })
            )

        var throttleClickedCount = 0

        compositeDisposable
            .add(
                binding
                    .btnThrottle
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        binding.tvClickTimeThrottle.text =
                            String.format("클릭된 횟수 : %d", ++throttleClickedCount)
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                binding
                    .etTextChange
                    .textChanges()
                    .subscribe({
                        binding.tvEditTextResult.text = String.format("입력된 text : %s", it)
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                binding
                    .etSearch
                    .textChanges()
                    .debounce(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        binding.tvSearchText.text = String.format("입력된 text : %s", it)
                    }, { it.printStackTrace() })
            )

        val idWatcher = binding.etId.textChanges().map { "$it" }
        val pwWatcher = binding.etPw.textChanges().map { "$it" }
        compositeDisposable
            .add(
                Observable
                    .combineLatest(
                        idWatcher,
                        pwWatcher,
                        BiFunction { idResult: String, pwResult: String ->
                            return@BiFunction idResult.isBlank() || pwResult.isBlank()
                        }
                    )
                    .subscribe({ blank ->
                        binding.btnLogin.isEnabled = !blank
                    }, { it.printStackTrace() })
            )
    }
}