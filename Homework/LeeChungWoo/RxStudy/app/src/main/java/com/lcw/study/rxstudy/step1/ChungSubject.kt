package com.lcw.study.rxstudy.step1

class ChungSubject<K> {
    private val observerList: MutableList<ChungObserver<K>> = ArrayList()
    var value: K? = null
        set(newValue) {
            if (field != newValue) {
                field = newValue
                for (chungObserver in observerList) {
                    chungObserver.notifyDataIsArrived(newValue)
                }
            }
        }

    fun subscribe(chungObserver: ChungObserver<K>) {
        observerList.add(chungObserver)
    }

    fun dispose(chungObserver: ChungObserver<K>) {
        observerList.remove(chungObserver)
    }
}