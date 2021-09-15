package com.rxstudy

interface MyObserver<T> {
    fun notifyDataIsArrived(value: T?)
}