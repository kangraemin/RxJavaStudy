//
//  ViewController.swift
//  Homework_1
//
//  Created by EunseokJang on 2022/04/06.
//

import UIKit

class ObserverPatternViewController: UIViewController {

    @IBOutlet weak var textField: PercentTextView!
    @IBOutlet weak var slider: PercentSliderView!
    @IBOutlet weak var lbl: PercentLbl!
    @IBOutlet weak var image: PercentImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("DDD")
    }
}

