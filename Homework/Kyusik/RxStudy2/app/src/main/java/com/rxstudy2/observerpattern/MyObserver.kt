package com.rxstudy2.observerpattern

interface MyObserver<T> {
    fun notifyDataIsArrived(value: T?)
}