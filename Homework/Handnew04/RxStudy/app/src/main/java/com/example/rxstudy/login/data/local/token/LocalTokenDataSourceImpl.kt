package com.example.rxstudy.login.data.local.token

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LocalTokenDataSourceImpl(private val localTokenDao: LocalTokenDao) : LocalTokenDataSource {
   override fun getToken(): Single<LocalTokenItem> {
      return localTokenDao
         .getToken()
         .subscribeOn(Schedulers.io())
   }

   override fun saveToken(tokenItem: LocalTokenItem): Completable {
      return localTokenDao
         .saveToken(tokenItem)
         .subscribeOn(Schedulers.io())
   }

   override fun deleteAllCachedToken(): Completable {
      return localTokenDao
         .deleteAllCachedToken()
         .subscribeOn(Schedulers.io())
   }

   override fun getTokenNotRx(): LocalTokenItem {
      return localTokenDao
         .getTokenNotRx()
   }

   override fun saveTokenNotRx(tokenItem: LocalTokenItem) {
      return localTokenDao
         .saveTokenNotRx(tokenItem)
   }

   override fun deleteAllCachedTokenNotRx() {
      return localTokenDao
         .deleteAllCachedTokenNotRx()
   }
}