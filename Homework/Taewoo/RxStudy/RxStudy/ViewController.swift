//
//  ViewController.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/04.
//

import UIKit
import RxSwift

class ViewController: UIViewController {
    static var progressSubject = Subject<Int>()
    var disposeBag:DisposeBag!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        disposeBag = DisposeBag()
        justExample()
    }
    
    func justExample() {
        // onNext 이벤트를 받았을 때 할 행동을 정의 한 경우
        _ = Observable.just("1")
            .subscribe{
                print("subscribe", $0)
            }
            .disposed(by: disposeBag)
        
        // onNext, onError 이벤트를 받았을 때 할 행동을 정의 한 경우
        _ = Observable.just("2")
            .subscribe(onNext: { data in
                print("subscribe", data)
            }, onError: { err in
                print("error", err)
            })
            .disposed(by: disposeBag)
        
        // onNext, onError, onComplete 이벤트를 받았을 때 할 행동을 정의 한 경우
        _ = Observable.just("3")
            .subscribe(onNext: { data in
                print("subscribe", data)
            }, onError: { err in
                print("error", err)
            }, onCompleted: {
                print("onCompleted")
            }, onDisposed: {
                print("onDisposed")
            })
            .disposed(by: disposeBag)
    }
}

