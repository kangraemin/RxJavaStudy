package com.example.rxstudy.login.data.local.token

import io.reactivex.Completable
import io.reactivex.Single

interface LocalTokenDataSource {
   fun getToken(): Single<LocalTokenItem>
   fun saveToken(tokenItem: LocalTokenItem): Completable
   fun deleteAllCachedToken(): Completable
   fun getTokenNotRx(): LocalTokenItem
   fun saveTokenNotRx(tokenItem: LocalTokenItem)
   fun deleteAllCachedTokenNotRx()
}