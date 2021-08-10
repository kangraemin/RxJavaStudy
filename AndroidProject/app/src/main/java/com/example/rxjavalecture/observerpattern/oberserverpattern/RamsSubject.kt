package com.example.rxjavalecture.observerpattern.oberserverpattern

import java.util.*

class RamsSubject<K> {
    private val observerList: MutableList<RamsObserver<K>> = ArrayList()
    var value: K? = null
        set(newValue) {
            if (field != newValue) {
                field = newValue
                for (ramsObserver in observerList) {
                    ramsObserver.notifyDataIsArrived(newValue)
                }
            }
        }

    fun subscribe(ramsObserver: RamsObserver<K>) {
        observerList.add(ramsObserver)
    }

    fun dispose(ramsObserver: RamsObserver<K>) {
        observerList.remove(ramsObserver)
    }
}