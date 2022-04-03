package kr.co.kimriri.observerpattern.oberserverpattern

public interface ririObserver<T> {
    fun notifyDataIsArrived(value: T)
    }