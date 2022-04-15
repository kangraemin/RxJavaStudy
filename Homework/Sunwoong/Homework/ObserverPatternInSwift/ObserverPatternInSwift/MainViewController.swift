//
//  MainViewController.swift
//  ObserverPatternInSwift
//
//  Created by 김선웅 on 2022/04/05.
//

import UIKit

class MainViewController: UIViewController {
    
    var slider = SliderView(id: "slider1")
    var textField = TextFieldView(id: "textField1")
    var imageView = ImageView(id: "imageView1")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        layout()
        view.backgroundColor = .systemBackground
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        view.endEditing(true)
    }

    
    private func layout() {
        
        imageView.translatesAutoresizingMaskIntoConstraints = false
        slider.translatesAutoresizingMaskIntoConstraints = false
        textField.translatesAutoresizingMaskIntoConstraints = false
        
        [
            imageView, slider, textField
        ].forEach { view.addSubview($0) }
        
        imageView.topAnchor.constraint(equalTo: view.topAnchor, constant: UIScreen.main.bounds.height / 5).isActive = true
        imageView.heightAnchor.constraint(equalToConstant: 300).isActive = true
        imageView.widthAnchor.constraint(equalToConstant: 300).isActive = true
        imageView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        
        slider.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        slider.topAnchor.constraint(equalTo: imageView.bottomAnchor, constant: 30).isActive = true
        slider.widthAnchor.constraint(equalTo: view.widthAnchor, constant: -40).isActive = true
        slider.heightAnchor.constraint(equalToConstant: 40).isActive = true
        
        textField.bottomAnchor.constraint(equalTo: imageView.topAnchor, constant: -20).isActive = true
        textField.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        textField.widthAnchor.constraint(equalToConstant: 200).isActive = true
        textField.heightAnchor.constraint(equalToConstant: 40).isActive = true

        
    }
    
}

