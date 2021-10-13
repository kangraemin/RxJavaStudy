//
//  CombiningOperatorsViewController.swift
//  RxStudy
//  9주차 과제 - RxCocoa를 사용하여 아이디 비밀번호 validation 체크하기
//  Created by Mingu Seo on 2021/10/13.
//


import Foundation
import UIKit
import RxSwift
import RxCocoa
import NSObject_Rx

class CombiningOperatorsViewController: UIViewController {
    @IBOutlet weak var btnLogin: UIButton!
    @IBOutlet weak var txtEmail: UITextField!
    @IBOutlet weak var txtPw: UITextField!
    @IBOutlet weak var imgEmail: UIImageView!
    @IBOutlet weak var imgPw: UIImageView!
    
    var txtEmailObservable = PublishSubject<Bool>().asObservable()
    var txtPasswordObservable = PublishSubject<Bool>().asObservable()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        txtEmail.becomeFirstResponder()
        bindingUI()
    }
    
    private func bindingUI() {
        txtEmailObservable = txtEmail.rx.text.orEmpty
            .map(validEmail)
            .distinctUntilChanged()
        
        txtPasswordObservable =  txtPw.rx.text.orEmpty
            .map(validPassword)
            .distinctUntilChanged()
        
        txtEmailObservable.bind(to: imgEmail.rx.isHidden).disposed(by: rx.disposeBag)
        txtPasswordObservable.bind(to: imgPw.rx.isHidden).disposed(by: rx.disposeBag)
        
        //runCombineLatestValidation()
        //runMergeValidation()
        runConcatValidation()
        //runZipValidation()
    }
}

//MARK: Validation
extension CombiningOperatorsViewController {
    
    //MARK: Merge
    private func runMergeValidation() {
        Observable.merge(txtEmailObservable,txtPasswordObservable)
            .bind(to: btnLogin.rx.isEnabled)
            .disposed(by: rx.disposeBag)
    }
    
    //MARK: Concat
    private func runConcatValidation() {
        Observable.concat(txtEmailObservable,txtPasswordObservable)
            .bind(to: btnLogin.rx.isEnabled)
            .disposed(by: rx.disposeBag)
    }
    
    //MARK: Zip
    private func runZipValidation() {
        Observable.zip(txtEmailObservable,txtPasswordObservable) { $0 && $1 }
            .bind(to: btnLogin.rx.isEnabled)
            .disposed(by: rx.disposeBag)
    }
    
    //MARK: CombineLatest
    private func runCombineLatestValidation() {
        Observable.combineLatest(txtEmailObservable,txtPasswordObservable) { $0 && $1 }
            .bind(to: btnLogin.rx.isEnabled)
            .disposed(by: rx.disposeBag)
    }
}

extension CombiningOperatorsViewController {
    //MARK: Merge
    private func runMergeExample() {
        // 1초마다 0, 1, 2, ..., 9 까지 숫자 데이터를 발행하는 observable
        let integerInterval = Observable
            .interval(.microseconds(1000), scheduler: ConcurrentDispatchQueueScheduler.init(qos: .default))
            .take(while: {$0 < 10} )
            .map { "integerInterval의 \($0) 번쨰 String 데이터" }

        // 1.5초마다 n번째 String 데이터를 발행하는 observable
        let stringInterval = Observable
            .interval(.microseconds(1500), scheduler: ConcurrentDispatchQueueScheduler.init(qos: .default))
            .take(while: { $0 < 10 })
            .map { "stringInterval의 \($0) 번째 String 데이터" }
        
        Observable.merge(integerInterval, stringInterval)
            .subscribe(onNext: {
                print($0)
            })
            .disposed(by: rx.disposeBag)
    }
    
    //MARK: Concat
    private func runConcatExample() {
        // 1초마다 0, 1, 2, ..., 9 까지 숫자 데이터를 발행하는 observable
        let integerInterval = Observable
            .interval(.microseconds(1000), scheduler: ConcurrentDispatchQueueScheduler.init(qos: .default))
            .take(while: {$0 < 10} )
            .map { "integerInterval의 \($0) 번쨰 String 데이터" }

        // 1.5초마다 n번째 String 데이터를 발행하는 observable
        let stringInterval = Observable
            .interval(.microseconds(1500), scheduler: ConcurrentDispatchQueueScheduler.init(qos: .default))
            .take(while: { $0 < 10 })
            .map { "stringInterval의 \($0) 번쨰 String 데이터" }
        
        Observable.concat(integerInterval, stringInterval)
            .subscribe(onNext: {
                print($0)
            })
            .disposed(by: rx.disposeBag)
    }
}

extension CombiningOperatorsViewController {
    //MARK: 이메일 유효성
    private func validEmail(_ emailAddress: String) -> Bool {
        var returnValue = true
        let emailRegEx = "[A-Z0-9a-z.-_]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}"
        
        do {
            let regex = try NSRegularExpression(pattern: emailRegEx)
            let nsString = emailAddress as NSString
            let results = regex.matches(in: emailAddress, range: NSRange(location: 0, length: nsString.length))
            if results.count == 0 { returnValue = false }
        } catch let error as NSError {
            print("invalid regex: \(error.localizedDescription)")
            returnValue = false
        }
        return returnValue
    }
    
    //MARK: 비밀번호 유효성
    private func validPassword(_ password: String) -> Bool {
        var returnValue = true
        let passwordRegEx = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!$%&?._-]).{8,16}$"
        
        do {
            let regex = try NSRegularExpression(pattern: passwordRegEx)
            let nsString = password as NSString
            let results = regex.matches(in: password, range: NSRange(location: 0, length: nsString.length))
            if results.count == 0 { returnValue = false }
        } catch let error as NSError {
            print("invalid regex: \(error.localizedDescription)")
            returnValue = false
        }
        return returnValue
    }
}
