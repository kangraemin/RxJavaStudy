package com.lcw.study.rxstudy.step1

interface ChungObserver<T> {
    fun notifyDataIsArrived(value: T?)
}