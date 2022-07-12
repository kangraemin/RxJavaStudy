package com.example.rxstudy.login.data.remote.base

data class BaseAPICallResult<T>(
   val result: T? = null,
   val throwable: Throwable? = null
)