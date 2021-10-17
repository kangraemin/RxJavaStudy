package com.example.rxjavalecture

import android.app.Application
import com.example.rxjavalecture.exercise.data.local.DatabaseClient
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins


class CustomApplication : Application() {
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