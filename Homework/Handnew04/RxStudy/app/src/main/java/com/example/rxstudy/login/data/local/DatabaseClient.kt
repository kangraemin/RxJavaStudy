package com.example.rxstudy.login.data.local

import android.content.Context
import androidx.room.Room
import com.example.rxstudy.login.data.local.token.LocalTokenDao

object DatabaseClient {
   private lateinit var appDatabase: AppDatabase

   fun createDatabase(context: Context) {
      appDatabase = Room.databaseBuilder(
         context.applicationContext,
         AppDatabase::class.java,
         "database"
      ).build()
   }

   fun tokenDao(): LocalTokenDao = appDatabase.localTokenDao()
}