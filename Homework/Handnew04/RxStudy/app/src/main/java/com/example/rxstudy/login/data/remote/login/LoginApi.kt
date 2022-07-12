package com.example.rxstudy.login.data.remote.login

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
   @POST("api/token/")
   fun loginRetrofitCall(@Body loginInfo: LoginInfo): Call<LoginItem>

   @POST("api/token/")
   fun loginRxStream(@Body loginInfo: LoginInfo): Single<LoginItem>
}