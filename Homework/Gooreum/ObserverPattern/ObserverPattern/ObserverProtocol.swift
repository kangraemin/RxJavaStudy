//
//  ObserverProtocol.swift
//  ObserverPattern
//
//  Created by Mingu Seo on 2021/08/22.
//

import Foundation

//Observer가 subject의 data가 변경되었을 때 처리해야 할 함수 제공
//모든 observer가 공통으로 사용하는 함수이기 때문에 observer가 protocol을 채택하게 함으로써 추상화 시킨다.
public protocol ObserverProtocol: AnyObject {
    func notifyDataIsChanged<T>(value: T)
}

