//
//  Subjectable.swift
//  ObserverPatternInSwift
//
//  Created by 김선웅 on 2022/04/05.
//

import Foundation

final class Subjectable: Subject{
    
    static let shared = Subjectable()
    
    private init() {}
    
    var observers: [Observerable] = []
    
    var alphaValue: Int = 0 {
        willSet(num) {
            
            self.observers.forEach {
                $0.update(value: num)
            }
            
        }
    }
    
    func subscribe(observer: Observerable) {
        self.observers.append(observer)
    }
    
    func unSubscribe(id: String) {
        self.observers = observers.filter { $0.id != id }
    }
    
}
