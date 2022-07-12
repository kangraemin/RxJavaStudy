package com.example.observerpattern

interface DefaultObserver<T> {
   fun notifyDataIsArrived(value : T)
}