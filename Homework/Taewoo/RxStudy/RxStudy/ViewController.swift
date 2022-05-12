//
//  ViewController.swift
//  RxStudy
//
//  Created by Taewoo Kim on 2022/04/04.
//

import UIKit
import RxSwift
import RxCocoa

class ViewController: UIViewController {
    static var progressSubject = Subject<Int>()
    var disposeBag:DisposeBag!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        disposeBag = DisposeBag()
        justExample()
        observableExample()
        singleExample()
        maybeExample()
        completableExample()
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
    
    func observableExample() {
        Observable.just(15)
            .observe(on: MainScheduler.instance)
            .subscribe(on: OperationQueueScheduler(operationQueue: .main))
            .subscribe { i in
                print("onNext", i)
                print(Thread.isMainThread)
            } onDisposed: {
                print("onDisposed")
            }
            .disposed(by: disposeBag)
    }
    
    func singleExample() {
        Single<Int>.create { single in
            single(.success(3))
            return Disposables.create()
        }
        .observe(on: MainScheduler.instance)
        .subscribe(on: ConcurrentDispatchQueueScheduler(qos: .background))
        .subscribe{
            switch $0 {
            case .success(let int):
                print(int)
            case .failure(let err):
                print(err)
            }
        }
        .disposed(by: disposeBag)
    }
    
    func maybeExample() {
        Maybe.just("Maybe")
            .observe(on: CurrentThreadScheduler.instance)
            .subscribe(on: SerialDispatchQueueScheduler(qos: .default))
            .subscribe { str in
                print(str)
            } onDisposed: {
                print("onMaybeDisposed")
            }
            .disposed(by: disposeBag)
    }
    
    func completableExample() {
        Completable.create { completable in
            completable(.completed)
            return Disposables.create()
        }
        .observe(on: MainScheduler.instance)
        .subscribe(on: SerialDispatchQueueScheduler(qos: .default))
        .subscribe {
            print("success")
        } onError: { err in
            print(err)
        } onDisposed: {
            print("onCompletableDisposed")
        }
        .disposed(by: disposeBag)
    }
}

