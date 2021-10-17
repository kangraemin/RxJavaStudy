package com.example.rxjavalecture.exercise.data.remote.base

data class BaseAPICallResult<T>(
    val result: T? = null,
    val throwable: Throwable? = null
)
