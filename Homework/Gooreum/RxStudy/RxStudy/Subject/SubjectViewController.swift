//
//  SubjectViewController.swift
//  RxStudy
//
//  Created by Mingu Seo on 2021/09/16.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa
import NSObject_Rx

class SubjectViewController: UIViewController {
    ///TODO : RxCocoa사용해서 버튼 액션 적용해보기
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    //MARK: PublishSubject
    @IBAction func btnPublishSubject(_ sender: Any) {
        let publishSubject = PublishSubject<String>()
        
        publishSubject.onNext(self.startTaskToGetFirstString())
        publishSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in first subscriber")
            }
            .disposed(by: self.rx.disposeBag)
        
        publishSubject.onNext(self.startTaskToGetSecondString())
        publishSubject.onNext(self.startTaskToGetThirdString())
        
        threadLog(message: "---------구분선---------")
        publishSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in second subscriber")
            }
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: BehaviorSubject
    @IBAction func btnBehaviorSubject(_ sender: Any) {
        let behaviorSubject = BehaviorSubject<String>(value: self.startTaskToGetFirstString())
        
        behaviorSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in first subscriber")
            }
            .disposed(by: self.rx.disposeBag)
        
        behaviorSubject.onNext(self.startTaskToGetSecondString())
        behaviorSubject.onNext(self.startTaskToGetThirdString())
        
        threadLog(message: "---------구분선---------")
        
        behaviorSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in second subscriber")
            }
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: AsyncSubject
    @IBAction func btnAsyncSubject(_ sender: Any) {
        let asyncSubject = AsyncSubject<String>()
        
        asyncSubject.onNext(self.startTaskToGetFirstString())
        asyncSubject.onNext(self.startTaskToGetSecondString())
        asyncSubject.onNext(self.startTaskToGetThirdString())
        asyncSubject.onCompleted()
        
        asyncSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in first subscriber")
            }
            .disposed(by: self.rx.disposeBag)
        
        threadLog(message: "---------구분선---------")
        
        asyncSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in second subscriber")
            }
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: ReplaySubject
    @IBAction func btnReplaySubject(_ sender: Any) {
        let replaySubject = ReplaySubject<String>.createUnbounded()

        replaySubject.onNext(self.startTaskToGetFirstString())
        replaySubject.onNext(self.startTaskToGetSecondString())
        replaySubject.onNext(self.startTaskToGetThirdString())

        replaySubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in first subscriber")
            }
            .disposed(by: self.rx.disposeBag)

        threadLog(message: "---------구분선---------")

        replaySubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in second subscriber")
            }
            .disposed(by: self.rx.disposeBag)

        
        let replayWithBufferSubject = ReplaySubject<String>.create(bufferSize: 1)
        
        replayWithBufferSubject.onNext(self.startTaskToGetFirstString())
        replayWithBufferSubject.onNext(self.startTaskToGetSecondString())
        replayWithBufferSubject.onNext(self.startTaskToGetThirdString())
        
        replayWithBufferSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in first subscriber")
            }
            .disposed(by: self.rx.disposeBag)
        
        threadLog(message: "---------구분선---------")
        
        replayWithBufferSubject
            .subscribe { [self] in
                threadLog(message: "$0 = \($0) in second subscriber")
            }
            .disposed(by: self.rx.disposeBag)
    }
}

extension SubjectViewController {
    private func threadLog<T>(message: T) {
        print("scheduler : ", "thread name = \(Thread.isMainThread ? "Main Thread" : "Background Thread" ) / message = \(message)")
    }
    private func startTaskToGetFirstString() -> String {self.threadLog(message: "Start task to emit 1"); return "1"}
    private func startTaskToGetSecondString() -> String {self.threadLog(message: "Start task to emit 2"); return "2"}
    private func startTaskToGetThirdString() -> String {self.threadLog(message: "Start task to emit 3"); return "3"}
}
