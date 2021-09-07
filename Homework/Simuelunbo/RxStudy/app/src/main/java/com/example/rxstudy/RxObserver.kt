package com.example.rxstudy

interface RxObserver<T> {
    fun notifyObserverUpdate(value: T)
}