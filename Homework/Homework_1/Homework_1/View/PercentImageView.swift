//
//  PercentImageView.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import Foundation
import UIKit

class PercentImageView: UIImageView, Observer {

    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        backgroundColor = .black
        Subject.shared.subscribe(observer: self)
    }
    
    func notifyDataIsArrived(value: Int) {
        alpha = CGFloat(value) / 100
    }
}
