package com.example.rxstudy

import android.app.Application
import com.example.rxstudy.login.data.local.DatabaseClient
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins

class RxApplication: Application() {
   override fun onCreate() {
      super.onCreate()
      RxJavaPlugins.setErrorHandler { e: Throwable ->
         if (e is UndeliverableException) {
            return@setErrorHandler
         }
      }
      DatabaseClient.createDatabase(this)
   }
}