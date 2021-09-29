package com.example.rxjavalecture.rxbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityRxBindingExampleBinding
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable
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
                        binding.tvClickTimeThrottle.text = String.format("클릭된 횟수 : %d", ++throttleClickedCount)
                    }, { it.printStackTrace() })
            )

        EditText

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
    }
}