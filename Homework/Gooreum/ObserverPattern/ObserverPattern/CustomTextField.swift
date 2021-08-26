//
//  PercentTextField.swift
//  ObserverPattern
//
//  Created by Mingu Seo on 2021/08/22.
//


import UIKit

class CustomTextField: ObserverProtocol {

    var txtParency: UITextField!
    
    init(txtParency: UITextField) {
        self.txtParency = txtParency
        MainViewController.progressSubject.subscribe(observer: self)
        self.txtParency.addTarget(self, action: #selector(self.textFieldDidChange(_:)), for: .editingChanged)
    }
    
    @objc func textFieldDidChange(_ sender: Any?) {
        if let value = self.txtParency.text {
            MainViewController.progressSubject.percentValue = value
        }
    }
    
    func notifyDataIsChanged<T>(value: T) {
        self.txtParency.text = "\(value)"
    }
    
    deinit {
        //구독해제
        MainViewController.progressSubject.dispose(observer: self)
    }
}
