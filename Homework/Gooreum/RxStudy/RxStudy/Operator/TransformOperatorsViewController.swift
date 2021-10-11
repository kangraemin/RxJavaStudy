//
//  TransformOperatorsViewController.swift
//  RxStudy
//  8주차 과제 - 데이터 흐름 제어 operator 활용해보기.
//  Created by Mingu Seo on 2021/10/01.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa
import NSObject_Rx

class TransformOperatorsViewController: UIViewController {
    
    @IBOutlet weak var btnMap: UIButton!
    @IBOutlet weak var btnFlatMap: UIButton!
    @IBOutlet weak var btnSwitchMap: UIButton!
    @IBOutlet weak var btnDebounce: UIButton!
    @IBOutlet weak var btnThrottle: UIButton!
    @IBOutlet weak var btnSample: UIButton!
    
    private var startedTime: Int64 = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
        runMapExample()
        runFlatMapExample()
        runSwitchMapExample()
        runDebounceExample()
        runThrottleExample()
        runSampleExample()
    }    
}

extension TransformOperatorsViewController {
    
    //MARK: Map
    private func runMapExample() {
        let observable = Observable<Int>.range(start: 0, count: 10)
        
        btnMap.rx.tap
            .flatMap { observable }
            .map { "\($0) is emitted" }
            .subscribe(onNext: { [weak self] in
                self?.threadLog(message: $0)
            })
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: FlatMap
    private func runFlatMapExample() {
        let observable = Observable<Int>.range(start: 0, count: 10)
        
        btnFlatMap.rx.tap
            .do(onNext: {[weak self] _ in self?.startedTime = Date().millisecondsSince1970})
                .flatMap { observable }
                .flatMap { [weak self] in (self?.getNameFromServerObservable(studentNumber: $0))!}
                .observe(on: MainScheduler.instance)
                .subscribe(onNext: {
                    self.timeStampedLog("data received ! \($0)")
                })
                .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: SwitchMap( FlatMapLatest )
    private func runSwitchMapExample() {
        //let observable = Observable<Int>.range(start: 0, count: 10)
        let observable = Observable<Int>.interval(.seconds(2), scheduler: MainScheduler.instance)
        btnSwitchMap.rx.tap
            .do(onNext: {[weak self] _ in self?.startedTime = Date().millisecondsSince1970})
                .flatMap { observable }
                .flatMapLatest { [weak self] in (self?.getNameFromServerObservable(studentNumber: $0))!}
                .observe(on: MainScheduler.instance)
                .subscribe(onNext: {
                    self.timeStampedLog("data received ! \($0)")
                })
                .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: Debounce
    private func runDebounceExample() {
        btnDebounce.rx.tap
            .debounce(.seconds(2), scheduler: MainScheduler.instance)
            .subscribe(onNext: {
                print("Debounce Button TAP!!!!")
            })
            .disposed(by: self.rx.disposeBag)
        
    }
    
    //MARK: Throttle
    private func runThrottleExample() {
        btnThrottle.rx.tap
            .throttle(.seconds(2), scheduler: MainScheduler.instance)
            .subscribe(onNext: {
                print("Throttle Button TAP!!!!")
            })
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: Sample
    private func runSampleExample() {
        self.startedTime = Date().millisecondsSince1970
       
        let observable = Observable<Int>.interval(.seconds(2), scheduler: MainScheduler.instance)
        var count = 0

        btnSample.rx.tap
            .do(onNext: {
                count += 1
                print(count)
            })
            .sample(observable)
            .observe(on: MainScheduler.instance)
            .subscribe(onNext: { [weak self] in self?.timeStampedLog("Sample Button TAP\(count)!!!!")
            }, onCompleted: { [weak self] in self?.timeStampedLog("completed")
            }, onDisposed: { [weak self] in self?.timeStampedLog("disposed")})
            .disposed(by: self.rx.disposeBag)
    }
    
}

extension TransformOperatorsViewController {
    private func timeStampedLog(_ message: String) {
        NSLog("Create Operator : thread name = \(Thread.isMainThread ? "Main Thread" : "Background Thread") 실행 후 흐른 시간 =  \(Date().millisecondsSince1970 - startedTime)/ message = \(message)")
    }
    
    private func threadLog<T>(message: T) {
        NSLog("scheduler : thread name = \(Thread.isMainThread ? "Main Thread" : "Background Thread" ) / message = \(message)")
    }
        
    private func startTaskToGetNameFromServer(studentNumber: Int) -> String {
        Thread.sleep(forTimeInterval: 1)
        timeStampedLog("Get name Gooreum num = \(studentNumber) from server")
        return "Gooreum num = \(studentNumber)"
    }
        
    private func getNameFromServerObservable(studentNumber: Int) -> Observable<String> {
        Observable
            .create{
                $0.onNext(self.startTaskToGetNameFromServer(studentNumber: studentNumber))
                return Disposables.create()
            }
            .subscribe(on: ConcurrentDispatchQueueScheduler.init(queue: DispatchQueue.global()))
            .do(onDispose: {
                self.timeStampedLog("Task is disposed !")})
    }
        
}

