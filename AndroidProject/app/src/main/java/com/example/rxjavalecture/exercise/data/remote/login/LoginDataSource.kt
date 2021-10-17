package com.example.rxjavalecture.exercise.data.remote.login

import io.reactivex.Single
import retrofit2.Callback

interface LoginDataSource {
    fun loginRetrofitCall(id: String, password: String, callback: Callback<LoginItem>)
    fun loginRxStream(id: String, password: String): Single<LoginItem>
}