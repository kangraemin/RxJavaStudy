package com.example.rxstudy.login.ui

import com.example.rxstudy.login.data.remote.base.BaseCallback
import com.example.rxstudy.login.data.remote.base.transformCompletableToSingleDefault
import com.example.rxstudy.login.data.repository.login.LoginRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class LoginViewModel(private val loginRepository: LoginRepository) {
   val loginSuccessSubject = PublishSubject.create<Boolean>()

   private val loginSubject = PublishSubject.create<Pair<String, String>>()
   private val compositeDisposable = CompositeDisposable()

   init {
      compositeDisposable
         .add(
            loginSubject
               .flatMapSingle {
                  loginRepository
                     .loginRxStream(it.first, it.second)
                     .transformCompletableToSingleDefault()
               }
               .subscribe(
                  { loginSuccessSubject.onNext(it.throwable == null) },
                  { it.printStackTrace() })
         )
   }

   fun loginRxStream(id: String, password: String) {
      loginSubject.onNext(Pair(id, password))
   }

   fun onViewCleared() {
      compositeDisposable.dispose()
   }
}