package com.example.rxjavalecture.exercise.data.repository.login

import android.util.Log
import com.example.rxjavalecture.exercise.data.local.token.LocalTokenDataSource
import com.example.rxjavalecture.exercise.data.local.token.LocalTokenMapper.mappingRemoteDataToLocal
import com.example.rxjavalecture.exercise.data.remote.base.BaseCallback
import com.example.rxjavalecture.exercise.data.remote.login.LoginDataSource
import com.example.rxjavalecture.exercise.data.remote.login.LoginItem
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryImpl(
    private val localTokenDataSource: LocalTokenDataSource,
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    private val getFirebaseTokenSingle: Single<String> by lazy {
        Single.create { emitter ->
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.d("FCM", "fail to get token = ${task.exception?.localizedMessage}")
                    emitter.onError(task.exception!!)
                }
                Log.d("FCM", "success to get token = ${task.result}")
                emitter.onSuccess(task.result!!)
            }
        }
    }

    override fun loginRetrofitCall(id: String, password: String, callback: BaseCallback<Unit>) {
        loginDataSource
            .loginRetrofitCall(id, password, object : Callback<LoginItem> {
                override fun onResponse(call: Call<LoginItem>, response: Response<LoginItem>) {
                    if (response.isSuccessful) {
                        try {
                            Thread {
                                localTokenDataSource
                                    .deleteAllCachedTokenNotRx()
                                localTokenDataSource
                                    .saveTokenNotRx(mappingRemoteDataToLocal(response.body()!!))
                            }.start()
                        } catch (e: Throwable) {
                            e.printStackTrace()
                        }
                        callback.onSuccess(Unit)
                    } else {
                        callback.onFail(Exception())
                    }
                }

                override fun onFailure(call: Call<LoginItem>, t: Throwable) {
                    callback.onFail(t)
                }
            })
    }

    override fun loginRxStream(id: String, password: String): Completable {
        return loginDataSource
            .loginRxStream(id, password)
            .flatMapCompletable {
                localTokenDataSource
                    .deleteAllCachedToken()
                    .andThen(localTokenDataSource.saveToken(mappingRemoteDataToLocal(it)))
                    .andThen(getFirebaseTokenSingle.ignoreElement())
            }
    }
}