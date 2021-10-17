package com.example.rxjavalecture.exercise.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxjavalecture.R
import com.example.rxjavalecture.databinding.ActivityLoginExampleBinding
import com.example.rxjavalecture.exercise.data.local.DatabaseClient
import com.example.rxjavalecture.exercise.data.local.token.LocalTokenDataSourceImpl
import com.example.rxjavalecture.exercise.data.remote.RetrofitClient
import com.example.rxjavalecture.exercise.data.remote.login.LoginDataSourceImpl
import com.example.rxjavalecture.exercise.data.repository.login.LoginRepositoryImpl
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction

class LoginExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginExampleBinding

    private val compositeDisposable = CompositeDisposable()

    private val viewModel: LoginViewModel by lazy {
        LoginViewModel(
            loginRepository = LoginRepositoryImpl(
                localTokenDataSource = LocalTokenDataSourceImpl(
                    localTokenDao = DatabaseClient.tokenDao()
                ),
                loginDataSource = LoginDataSourceImpl(
                    loginApi = RetrofitClient.loginAPI
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_example)

        compositeDisposable
            .add(
                Observable
                    .combineLatest(
                        binding.etId
                            .textChanges(),
                        binding.etPassword
                            .textChanges(),
                        BiFunction { id: CharSequence, password: CharSequence -> return@BiFunction id.isNotEmpty() && password.isNotEmpty() }
                    )
                    .subscribe({
                        binding.btnLoginNotRx.isEnabled = it
                        binding.btnLogin.isEnabled = it
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                binding
                    .btnLogin
                    .clicks()
                    .subscribe({
                        viewModel.loginRxStream(
                            binding.etId.text.toString(),
                            binding.etPassword.text.toString()
                        )
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                binding
                    .btnLoginNotRx
                    .clicks()
                    .subscribe({
                        viewModel.loginRetrofitCall(
                            binding.etId.text.toString(),
                            binding.etPassword.text.toString()
                        )
                    }, { it.printStackTrace() })
            )

        compositeDisposable
            .add(
                viewModel
                    .loginSuccessSubject
                    .subscribe({ successToLogin ->
                        if (successToLogin) {
                            binding.tvLoginResult.text =
                                String.format(getString(R.string.login_result_format, "로그인 성공"))
                        } else {
                            binding.tvLoginResult.text =
                                String.format(getString(R.string.login_result_format, "로그인 실패"))
                        }
                    }, { it.printStackTrace() })
            )

        binding.tvLoginResult.text = String.format(getString(R.string.login_result_format, "로그인 시도 전"))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onViewCleared()
    }
}