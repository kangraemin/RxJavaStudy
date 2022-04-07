//
//  Subject.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/04.
//

import Foundation

class Subject<T> {
    private var observerList = [ObserverProtocol]()
    var value: T? {
        get {
            return self.value
        }
        set {
            if let newValue = newValue {
                for observer in observerList{
                    observer.notifyDataIsChanged(val: newValue)
                }
            }
        }
    }
    
    func subscribe(obs: ObserverProtocol) {
        if !observerList.contains(where: {$0 === obs}){
            observerList.append(obs)
        }
    }
    
    func dispose(obs: ObserverProtocol) {
        if let idx = observerList.firstIndex(where: {$0 === obs}){
            observerList.remove(at: idx)
        }
    }
}
