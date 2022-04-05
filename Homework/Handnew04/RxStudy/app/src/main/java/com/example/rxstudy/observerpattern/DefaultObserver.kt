package com.example.rxstudy.observerpattern

interface DefaultObserver<T> {
   fun notifyDataIsArrived(value : T)
}