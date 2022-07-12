package com.example.rxstudy.login.data.remote.base

interface BaseCallback<T> {
   fun onSuccess(data: T)
   fun onFail(throwable: Throwable)
}