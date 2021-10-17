package com.example.rxjavalecture.exercise.data.local.token

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface LocalTokenDao {
    @Query(value = "SELECT * FROM token LIMIT 1")
    fun getToken(): Single<LocalTokenItem>

    @Insert
    fun saveToken(tokenItem: LocalTokenItem): Completable

    @Query(value = "DELETE FROM token")
    fun deleteAllCachedToken(): Completable

    @Query(value = "SELECT * FROM token LIMIT 1")
    fun getTokenNotRx(): LocalTokenItem

    @Insert
    fun saveTokenNotRx(tokenItem: LocalTokenItem)

    @Query(value = "DELETE FROM token")
    fun deleteAllCachedTokenNotRx()
}