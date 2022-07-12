package com.example.rxstudy.login.data.repository.login

import com.example.rxstudy.login.data.local.token.LocalTokenDataSource
import com.example.rxstudy.login.data.local.token.LocalTokenMapper.mappingRemoteDataToLocal
import com.example.rxstudy.login.data.remote.base.BaseCallback
import com.example.rxstudy.login.data.remote.login.LoginDataSource
import com.example.rxstudy.login.data.remote.login.LoginItem
import io.reactivex.Completable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositoryImpl(
   private val localTokenDataSource: LocalTokenDataSource,
   private val loginDataSource: LoginDataSource
) : LoginRepository {

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
               //.andThen(getFirebaseTokenSingle.ignoreElement())
         }
   }
}