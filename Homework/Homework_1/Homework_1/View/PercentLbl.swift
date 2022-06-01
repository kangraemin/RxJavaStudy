//
//  PercentLbl.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import Foundation
import UIKit

class PercentLbl: UILabel, Observer {

    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        Subject.shared.subscribe(observer: self)
    }
    
    func notifyDataIsArrived(value: Int) {
        text = "투명도: \(value)%"
    }
}

