package com.example.rxjavalecture.exercise.data.remote.base

interface BaseCallback<T> {
    fun onSuccess(data: T)
    fun onFail(throwable: Throwable)
}