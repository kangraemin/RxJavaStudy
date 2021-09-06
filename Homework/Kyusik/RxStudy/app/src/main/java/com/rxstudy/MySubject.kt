package com.rxstudy

class MySubject<K> {
    private val observerList: MutableList<MyObserver<K>> = ArrayList()
    var value: K? = null
        set(newValue) {
            if (field != newValue) {
                field = newValue
                for (mObserver in observerList) {
                    mObserver.notifyDataIsArrived(newValue)
                }
            }
        }

    fun subscribe(observer: MyObserver<K>) {
        observerList.add(observer)
    }

    fun dispose(observer: MyObserver<K>) {
        observerList.remove(observer)
    }
}