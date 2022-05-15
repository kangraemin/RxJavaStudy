package com.example.rxjavalecture.observerpattern.oberserverpattern

import com.example.rxkotlin.oberserverpattern.RxKotlinObserver
import java.util.*

class RxKotlinSubject<K> {
    private val observerList: MutableList<RxKotlinObserver<K>> = ArrayList()
    var value: K? = null
        set(newValue) {
            if (field != newValue) {
                field = newValue
                for (ramsObserver in observerList) {
                    ramsObserver.notifyDataIsArrived(newValue)
                }
            }
        }

    fun subscribe(rxKotlinObserver: RxKotlinObserver<K>) {
        observerList.add(rxKotlinObserver)
    }

    fun dispose(rxKotlinObserver: RxKotlinObserver<K>) {
        observerList.remove(rxKotlinObserver)
    }
}