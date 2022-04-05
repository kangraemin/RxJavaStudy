//
//  ObsStepper.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/05.
//

import UIKit

class ObsStepper: UIStepper, ObserverProtocol {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupViews()
    }
    
    private func setupViews() {
        ViewController.progressSubject.subscribe(obs: self)
        self.addTarget(self, action: #selector(stepperAction), for: .valueChanged)
    }
    
    @objc func stepperAction(_ sender: UIStepper){
        ViewController.progressSubject.value = Int(sender.value)
    }
    
    func notifyDataIsChanged<T>(val: T) {
        if let val = val as? Int{
            self.value = Double(val)
        }
    }
    
    deinit {
        ViewController.progressSubject.dispose(obs: self)
    }
}
