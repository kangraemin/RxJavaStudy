//
//  Observer.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import Foundation

protocol Observer: AnyObject {
    func notifyDataIsArrived(value: Int)
}

protocol Observable {
    mutating func subscribe(observer: Observer)
    mutating func dispose(observer: Observer)
}
