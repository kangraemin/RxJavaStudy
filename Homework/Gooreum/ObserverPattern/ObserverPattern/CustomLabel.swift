//
//  PercentLabel.swift
//  ObserverPattern
//
//  Created by Mingu Seo on 2021/08/22.
//

import UIKit

class CustomLabel: ObserverProtocol {
    
    var lbParency: UILabel!
    
    init(lbParency: UILabel) {
        self.lbParency = lbParency
        MainViewController.progressSubject.subscribe(observer: self)
    }
    
    func notifyDataIsChanged<T>(value: T) {
        self.lbParency.text = "\(value)"
    }
}

