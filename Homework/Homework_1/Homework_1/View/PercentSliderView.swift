//
//  SliderView.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import Foundation
import UIKit

class PercentSliderView: UISlider, Observer {
   
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        minimumValue = 0
        maximumValue = 100
        addTarget(self, action: #selector(updated), for: .valueChanged)
        Subject.shared.subscribe(observer: self)
    }

    @objc func updated(sender: UISlider) {
        Subject.shared.value = Int(sender.value)
    }
    
    func notifyDataIsArrived(value: Int) {
        self.value = Float(value)
    }
}
