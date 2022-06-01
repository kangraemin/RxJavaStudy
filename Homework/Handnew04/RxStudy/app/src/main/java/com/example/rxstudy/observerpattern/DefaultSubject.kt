package com.example.rxstudy.observerpattern

import java.util.ArrayList

class DefaultSubject<K> {
   private val observers: MutableList<DefaultObserver<K>> = ArrayList()

   // TODO:  value를 nonNull로 만들 방법은 없을까? null로 초기화 하고 다시 null체크를 하는게 좋아보이진 않는데.
   var value: K? = null
      set(newValue) {
         if (field != newValue && newValue != null) {
            field = newValue
            for (observer in observers) {
               observer.notifyDataIsArrived(newValue)
            }
         }
      }

   fun subscribe(observer: DefaultObserver<K>) {
      observers.add(observer)
   }

   fun dispose(observer: DefaultObserver<K>) {
      observers.remove(observer)
   }

   fun disposeAll() {
      observers.clear()
   }
}