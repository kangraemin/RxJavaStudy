//
//  Subject.swift
//  ObserverPatternInSwift
//
//  Created by 김선웅 on 2022/04/05.
//

import Foundation

protocol Subject {
    var observers: [Observerable] { get }
    func subscribe(observer: Observerable)
    func unSubscribe(id: String)
}

protocol Observerable {
    var id: String { get }
    func update(value: Int)
}
