//
//  ObsProgressView.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/04.
//

import UIKit

class ObsProgressView: UIProgressView, ObserverProtocol {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupViews()
    }
    
    private func setupViews() {
        //textfield 나 stepper의 변경이 일어날 때 progress를 변경하기위해 progress를 구독한다.
        ViewController.progressSubject.subscribe(obs: self)
    }
    
    func notifyDataIsChanged<T>(val: T) {
        if let val = val as? Int{
            self.progress = Float(val)/100
        }
    }
    
    deinit {
        ViewController.progressSubject.dispose(obs: self)
    }
}
