package com.example.rxstudy.login.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxstudy.databinding.ActivityExampleLoginBinding
import com.example.rxstudy.login.data.local.DatabaseClient
import com.example.rxstudy.login.data.local.token.LocalTokenDataSourceImpl
import com.example.rxstudy.login.data.remote.RetrofitClient
import com.example.rxstudy.login.data.remote.login.LoginDataSourceImpl
import com.example.rxstudy.login.data.repository.login.LoginRepositoryImpl
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.disposables.CompositeDisposable

class LoginExampleActivity : AppCompatActivity() {
   private val binding: ActivityExampleLoginBinding by lazy {
      ActivityExampleLoginBinding.inflate(layoutInflater)
   }

   private val compositeDisposable = CompositeDisposable()

   private val viewModel: LoginViewModel by lazy {
      LoginViewModel(
         loginRepository = LoginRepositoryImpl(
            localTokenDataSource = LocalTokenDataSourceImpl(
               localTokenDao = DatabaseClient.tokenDao()
            ), loginDataSource = LoginDataSourceImpl(
               loginApi = RetrofitClient.loginApi
            )
         )
      )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      setListener()
   }

   private fun setListener() {
      val idChanges = binding.etId.textChanges()
      val passwordChanges = binding.etPw.textChanges()

      compositeDisposable.add(
         binding.btLogin.clicks().subscribe({
            viewModel.loginRxStream(
               binding.etId.text.toString(), binding.etPw.text.toString()
            )
         }, {})
      )
      compositeDisposable.add(
         viewModel.loginSuccessSubject.subscribe({ successToLogin ->
            if (successToLogin) {
               binding.tvResult.text = "로그인 성공"
            } else {
               binding.tvResult.text = "로그인 실패"
            }
         }, { it.printStackTrace() })
      )
   }

   override fun onDestroy() {
      super.onDestroy()
      viewModel.onViewCleared()
      compositeDisposable.dispose()
   }
}