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
    
    // 일반적인 api통신같은 비동기 작업에 사용 합니다
    let create = Observable<String>.create { observer in
        observer.onNext("response")
        
        return Disposables.create()
    }
    
    // 조건별로 다른 옵저버블을 리턴할수도 있습니다.
    let defferd = Observable<String>.deferred {
        if true {
            return Observable.of("true")
        } else {
            return Observable.of("false")
        }
    }
    
    // 딱 단 하나의 값만 방출하고 싶을때 사용합니다.
    let just = Observable.just("hello")

    // 일반적인 input event에 사용합니다.
    let publishSubject = PublishSubject<String>()
    // 구독시에 데이터 방출이 없어도 기본값을 가지고 싶을 때 사용합니다.
    let behaviorSubject  = BehaviorSubject<String>(value: "behavior")
    // 구독시에 이전 값들을 사용하고 싶을 때 사용합니다.
    let replaySubject = ReplaySubject<String>.create(bufferSize: 2)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        publishSubject.subscribe { event in
            print("event!! \(event)")
        } onError: { error in
            print("error!! \(error.localizedDescription)")
        } onCompleted: {
            print("complete")
        } onDisposed: {
            print("dispose!")
        }.disposed(by: disposeBag)
        
        publishSubject.subscribe { event in
            print("behavior value \(event)")
        } onError: { error in
            print("error!! \(error.localizedDescription)")
        } onCompleted: {
            print("complete")
        } onDisposed: {
            print("dispose!")
        }.disposed(by: disposeBag)
        
        replaySubject.subscribe { event in
            print("replay value \(event)")
        } onError: { error in
            print("error!! \(error.localizedDescription)")
        } onCompleted: {
            print("complete")
        } onDisposed: {
            print("dispose!")
        }.disposed(by: disposeBag)

        publishSubject.onNext("publish subject!")
        publishSubject.onCompleted()
        
        behaviorSubject.onCompleted()
        
        replaySubject.onNext("replay1")
        replaySubject.onNext("replay2")

        replaySubject.subscribe { event in
            print("replay2 value \(event)")
        } onError: { error in
            print("error!! \(error.localizedDescription)")
        } onCompleted: {
            print("complete")
        } onDisposed: {
            print("dispose!")
        }.disposed(by: disposeBag)
        
        
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

