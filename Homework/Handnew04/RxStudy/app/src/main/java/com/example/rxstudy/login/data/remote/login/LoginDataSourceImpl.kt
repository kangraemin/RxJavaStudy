package com.example.rxstudy.login.data.remote.login

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Callback

class LoginDataSourceImpl(private val loginApi: LoginApi) : LoginDataSource {
   override fun loginRetrofitCall(id: String, password: String, callback: Callback<LoginItem>) {
      loginApi.loginRetrofitCall(LoginInfo(id, password))
         .enqueue(callback)
   }

   override fun loginRxStream(id: String, password: String): Single<LoginItem> {
      return loginApi
         .loginRxStream(LoginInfo(id, password))
         .subscribeOn(Schedulers.io())
   }

}