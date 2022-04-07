//
//  ObsTF.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/04.
//

import UIKit

class ObsTF: UITextField, ObserverProtocol {
    func notifyDataIsChanged<T>(val: T) {
        if let val = val as? Int{
            self.text = "\(val)"
        }
    }
    
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
        self.addTarget(self, action: #selector(changeTF), for: .editingChanged)
    }
    
    @objc func changeTF(_ sender: Any) {
        if let tf = sender as? UITextField, let str = tf.text, let num = Int(str), num >= 0{
            ViewController.progressSubject.value = num
        }else{
            ViewController.progressSubject.value = 0
        }
    }
    
    deinit {
        ViewController.progressSubject.dispose(obs: self)
    }
}
