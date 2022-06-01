//
//  Subject.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import Foundation

struct Subject: Observable {
    
    static var shared = Subject()
    
    var observerList = [Observer]()

    var value: Int = 0 {
        willSet(newValue) {
            observerList.forEach { $0.notifyDataIsArrived(value: newValue )}
        }
    }

    mutating func subscribe(observer: Observer) {
        observerList.append(observer)
    }
    
    mutating func dispose(observer: Observer) {
        observerList = observerList.filter { $0 === observer }
    }
}
