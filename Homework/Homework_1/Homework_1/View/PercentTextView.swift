//
//  PercentTextView.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import Foundation
import UIKit

class PercentTextView: UITextField, Observer {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        addTarget(self, action: #selector(updated), for: .editingChanged)
        Subject.shared.subscribe(observer: self)
    }
    
    @objc func updated(sender: UITextField) {
        if let value = Int(sender.text ?? "") {
            Subject.shared.value = value
        }
    }
    
    func notifyDataIsArrived(value: Int) {
        text = String(value)
    }
    
    
    
}
