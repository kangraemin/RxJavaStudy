//
//  ObserverProtocol.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/04.
//

import Foundation
protocol ObserverProtocol: AnyObject {func notifyDataIsChanged<T>(val: T)}
