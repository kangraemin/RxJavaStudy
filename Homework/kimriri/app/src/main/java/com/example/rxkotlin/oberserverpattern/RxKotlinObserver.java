package com.example.rxkotlin.oberserverpattern;

public interface RxKotlinObserver<T> {
    void notifyDataIsArrived(T value);
}
