//
//  ViewController.swift
//  Homework
//
//  Created by 김선웅 on 2022/05/05.
//

import UIKit
import RxSwift

class ViewController: UIViewController {

    let disposeBag = DisposeBag()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        Observable<String>.create { observer in
            observer.onNext("hello~")
            observer.onNext("hi~")
            observer.onNext("Sunwoong")
            observer.onNext("Kim")
            
            return Disposables.create()
        }.subscribe(onNext: {
            print($0)
        })
            .disposed(by: disposeBag)
        
        
        let singleOfJust = Single<String>.just("just~~single~~")
        
        singleOfJust.subscribe { event in
            switch event {
            case .success(let success):
                print("single Success")
            case .failure(let error):
                print("single failure")
            }
        }.disposed(by: disposeBag)
        
        Maybe<String>.create { observer in
            observer(.success("success maybe~~"))
            
            return Disposables.create()
        }.subscribe { event in
            switch event {
            case .success(let success):
                print(success)
            case .completed:
                print("completed")
            case .error(let error):
                print(error.localizedDescription)
            }
        }.disposed(by: disposeBag)
        
        
        Completable.create { observer in
            observer(.completed)
            
            return Disposables.create()
        }.subscribe { event in
            switch event {
            case .completed:
                print("completed!")
            case .error(let error):
                print(error.localizedDescription)
            }
        }.disposed(by: disposeBag)
        
    }

}

