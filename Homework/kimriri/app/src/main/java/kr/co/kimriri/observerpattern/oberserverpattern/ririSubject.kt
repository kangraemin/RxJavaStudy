package kr.co.kimriri.observerpattern.oberserverpattern

import java.util.*

class ririSubject<K> {

    private val observerList : MutableList<ririObserver<K>> = ArrayList()

    var value: K? = null
    set(newvalue) {

        if(field != newvalue){
            field = newvalue
            for (ririObserver in observerList) {
                if (newvalue != null) {
                    ririObserver.notifyDataIsArrived(newvalue)
                }
            }
        }
    }
    fun subscribe(ririObserver: ririObserver<K>) {
        observerList.add(ririObserver)
    }

    fun dispose(ririObserver: ririObserver<K>) {
        observerList.remove(ririObserver)
    }

}