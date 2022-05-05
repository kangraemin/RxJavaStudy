//
//  ImageVIew.swift
//  ObserverPatternInSwift
//
//  Created by 김선웅 on 2022/04/05.
//

import Foundation
import UIKit

final class ImageView: UIImageView, Observerable {
    
    let id: String
    let subject = Subjectable.shared
    
    init(id: String) {
        self.id = id
        
        super.init(frame: .zero)
        
        backgroundColor = .systemRed
        subject.subscribe(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func update(value: Int) {
        let floatValue: CGFloat = CGFloat(value)
        self.alpha = floatValue / 100
    }
    
}
