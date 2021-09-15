package com.example.rxstudy.observerPattern

class RxSubject<T> {
    private val observerList: MutableList<RxObserver<T>> = ArrayList()
    var value: T? = null
        set(newValue) {
            if (field != newValue) {
                field = newValue
                for (ramsObserver in observerList) {
                    if (newValue != null) {
                        ramsObserver.notifyObserverUpdate(newValue)
                    }
                }
            }
        }

    fun subscribe(mObserver: RxObserver<T>) {
        observerList.add(mObserver)
    }

    fun dispose(mObserver: RxObserver<T>) {
        observerList.remove(mObserver)
    }

}