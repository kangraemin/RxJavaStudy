//
//  ViewController.swift
//  ObserverPattern
//
//  Created by Mingu Seo on 2021/08/22.
//

import UIKit

class MainViewController: UIViewController {
    
    @IBOutlet weak var txtTransparency1: UITextField!
    @IBOutlet weak var txtTransparency2: UITextField!
    @IBOutlet weak var txtTransparency3: UITextField!
    @IBOutlet weak var lbTransparency: UILabel!
    
    static var progressSubject = Subject<String>()
    
    var percentTextField1 : CustomTextField?
    var percentTextField2 : CustomTextField?
    var percentTextField3 : CustomTextField?
    var percentLabel : CustomLabel?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.percentTextField1 = CustomTextField(txtParency: self.txtTransparency1)
        self.percentTextField2 = CustomTextField(txtParency: self.txtTransparency2)
        self.percentTextField3 = CustomTextField(txtParency: self.txtTransparency3)
        self.percentLabel = CustomLabel(lbParency: self.lbTransparency)
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        //구독해제
        MainViewController.progressSubject.dispose()
    }
}

