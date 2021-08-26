//
//  Subject.swift
//  ObserverPattern
//
//  Created by Mingu Seo on 2021/08/22.
//

import Foundation

class Subject<T>  {
    
    private var observerList : [ObserverProtocol] = []
    private var value: T?
    
    var percentValue: T? {
        get {
            if let value = self.value {
                return value
            }else {
                fatalError()
            }
        }
        set {
            if let newValue = newValue {
                self.value = newValue
                for observer in observerList {
                    observer.notifyDataIsChanged(value: newValue )
                }
            }
            
        }
    }
    
    //구독
    func subscribe(observer: ObserverProtocol) {
        print("\(observer) tries to subscribe the subject ")
        observerList.append(observer)
    }
    
    //구독취소
    func dispose() {
        observerList.removeAll()
    }
}
