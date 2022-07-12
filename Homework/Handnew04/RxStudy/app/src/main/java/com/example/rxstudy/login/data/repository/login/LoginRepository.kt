package com.example.rxstudy.login.data.repository.login

import com.example.rxstudy.login.data.remote.base.BaseCallback
import io.reactivex.Completable

interface LoginRepository {
   fun loginRetrofitCall(id: String, password:String, callback: BaseCallback<Unit>)
   fun loginRxStream(id: String, password: String): Completable
}