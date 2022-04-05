//
//  ObsView.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/05.
//

import UIKit

class ObsView: UIView, ObserverProtocol {
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
    }
    
    func notifyDataIsChanged<T>(val: T) {
        if let val = val as? Int{
            self.backgroundColor = UIColor.black.withAlphaComponent(CGFloat(Double(val)/100))
        }
    }
    
    deinit {
        ViewController.progressSubject.dispose(obs: self)
    }
}
