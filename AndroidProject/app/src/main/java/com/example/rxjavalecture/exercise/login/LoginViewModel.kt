package com.example.rxjavalecture.exercise.login

import com.example.rxjavalecture.exercise.data.remote.base.BaseCallback
import com.example.rxjavalecture.exercise.data.remote.base.transformCompletableToSingleDefault
import com.example.rxjavalecture.exercise.data.repository.login.LoginRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
    private val loginRepository: LoginRepository
) {

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
                    .subscribe({
                        loginSuccessSubject.onNext(it.throwable == null)
                    }, { it.printStackTrace() })
            )
    }

    fun loginRetrofitCall(id: String, password: String) {
        loginRepository.loginRetrofitCall(id, password, object : BaseCallback<Unit> {
            override fun onSuccess(data: Unit) {
                loginSuccessSubject.onNext(true)
            }

            override fun onFail(throwable: Throwable) {
                loginSuccessSubject.onNext(false)
            }
        })
    }

    fun loginRxStream(id: String, password: String) {
        loginSubject.onNext(Pair(id, password))
    }

    fun onViewCleared() {
        compositeDisposable.dispose()
    }
}