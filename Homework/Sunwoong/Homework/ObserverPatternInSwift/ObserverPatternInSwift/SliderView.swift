//
//  SliderView.swift
//  ObserverPatternInSwift
//
//  Created by 김선웅 on 2022/04/05.
//

import Foundation
import UIKit

final class SliderView: UISlider, Observerable {
    let id: String
    let subject = Subjectable.shared
    
    init(id: String) {
        self.id = id
        super.init(frame: .zero)
        
        subject.subscribe(observer: self)
        addTarget(self, action: #selector(sliderValueChanged), for: .valueChanged)
        minimumValue = 0
        maximumValue = 100
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func update(value: Int) {
        self.value = Float(value)
    }
    
    @objc func sliderValueChanged(sender: UISlider) {
        print(sender.value)
        subject.alphaValue = Int(sender.value)
    }

}
