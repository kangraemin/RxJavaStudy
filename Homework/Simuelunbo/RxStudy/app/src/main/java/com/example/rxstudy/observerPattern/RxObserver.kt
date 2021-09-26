package com.example.rxstudy.observerPattern

interface RxObserver<T> {
    fun notifyObserverUpdate(value: T)
}