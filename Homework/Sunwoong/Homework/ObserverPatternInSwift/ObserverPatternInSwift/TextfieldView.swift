//
//  TextfieldView.swift
//  ObserverPatternInSwift
//
//  Created by 김선웅 on 2022/04/05.
//

import Foundation
import UIKit

final class TextFieldView: UITextField, Observerable {
    
    let id: String
    let subject = Subjectable.shared
    
    init(id: String) {
        self.id = id
        super.init(frame: .zero)
        
        subject.subscribe(observer: self)
        backgroundColor = .systemBlue.withAlphaComponent(0.5)
        addTarget(self, action: #selector(textFieldValueDidChange(_:)), for: .editingChanged)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func update(value: Int) {
        text = "\(value)"
    }
    
    @objc func textFieldValueDidChange(_ sender: UITextField) {
        if let text = sender.text,
           let alphaValue = Int(text) {
            subject.alphaValue = alphaValue
        }
    }
    
}
