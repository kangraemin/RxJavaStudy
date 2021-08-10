package com.example.rxjavalecture.observerpattern.oberserverpattern;

public interface RamsObserver<T> {
    void notifyDataIsArrived(T value);
}
