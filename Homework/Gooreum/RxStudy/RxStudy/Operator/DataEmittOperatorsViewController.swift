//
//  DataEmittOperators.swift
//  RxStudy
//  7주차 과제 - 데이터 발행 operator 사용해보기
//  Created by Mingu Seo on 2021/10/01.
//

import Foundation
import UIKit
import RxSwift
import RxCocoa
import NSObject_Rx

class DataEmittOperatorsViewController: UIViewController {
    
    @IBOutlet weak var btnCreate: UIButton!
    @IBOutlet weak var btnJust: UIButton!
    @IBOutlet weak var btnDefer: UIButton!
    @IBOutlet weak var btnFromCallable: UIButton!
    @IBOutlet weak var btnFromIterable: UIButton!
    @IBOutlet weak var btnInterval: UIButton!
    @IBOutlet weak var btnTimer: UIButton!
    @IBOutlet weak var btnRange: UIButton!
    
    private var startedTime: Int64 = 0
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    
    //MARK: 1.Create
    @IBAction func btnCreate(_ sender: Any) {
        runCreateExample()
    }
    
    //MARK: 2.Just
    @IBAction func btnJust(_ sender: Any) {
        runJustExample()
        
    }
    
    //MARK: 3.Defer
    @IBAction func btnDefer(_ sender: Any) {
        runDeferExample()
    }
    
    //MARK: 4.FromCallable
    @IBAction func btnFromCallable(_ sender: Any) {
        runFromCallableExample()
    }
    
    //MARK: 5.FromIterable
    @IBAction func btnFromIterable(_ sender: Any) {
        runFromIterableExample()
    }
    
    //MARK: 6.Interval
    @IBAction func btnInterval(_ sender: Any) {
        runIntervalExample()
    }
    
    //MARK: 7.Timer
    @IBAction func btnTimer(_ sender: Any) {
        runTimerExample()
    }
    
    //MARK: 8.Range
    @IBAction func btnRange(_ sender: Any) {
        runRangeExample()
    }
}

extension DataEmittOperatorsViewController {
    
    //MARK: Create
    private func runCreateExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        Observable<String>.create() { emitter in
            emitter.onNext(self.startTaskToGetFirstString())
            emitter.onNext(self.startTaskToGetSecondString())
            emitter.onNext(self.startTaskToGetThirdString())
            
            return Disposables.create()
        }
        .subscribe()
        .disposed(by: self.rx.disposeBag)
        
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: Just
    private func runJustExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        Observable<[String]>.just([startTaskToGetFirstString(), startTaskToGetSecondString(), startTaskToGetThirdString()])
            .subscribe()
            .disposed(by: self.rx.disposeBag)
        
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: Defer
    private func runDeferExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        Observable<String>.deferred {
            Observable<String>.create() { emitter in
                emitter.onNext(self.startTaskToGetFirstString())
                emitter.onNext(self.startTaskToGetSecondString())
                emitter.onNext(self.startTaskToGetThirdString())
                return Disposables.create()
            }
        }
        .subscribe()
        .disposed(by: self.rx.disposeBag)
        
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: FromCallable -> 없음
    private func runFromCallableExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        let strings = [startTaskToGetFirstString(), startTaskToGetSecondString(), startTaskToGetThirdString()]
        
        Observable<String>.from(strings)
            .subscribe()
            .disposed(by: self.rx.disposeBag)
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: FromIterable -> 없음
    private func runFromIterableExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: Interval
    private func runIntervalExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        
        Observable<Int>.interval(.seconds(1), scheduler: MainScheduler.instance)
            .subscribe(onNext: { [weak self] in
                self?.timeStampedLog(message: "\($0)")
            })
            .disposed(by: self.rx.disposeBag)
        
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: Timer
    private func runTimerExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        //5초후에 next 이벤트 발행 후,onCompleted 됨
        Observable<Int>.timer(RxTimeInterval.seconds(5), scheduler: MainScheduler.instance)
            .map { $0+1 }
            .subscribe(onNext: { [weak self] in
                self?.timeStampedLog(message: "\($0)")},
                       onError: { print($0)},
                       onCompleted: { self.timeStampedLog(message: "On COmplete")})
            .disposed(by: self.rx.disposeBag)
                
        timeStampedLog(message: "------- end task -------")
    }
    
    //MARK: Range
    private func runRangeExample() {
        startedTime = Date().millisecondsSince1970
        timeStampedLog(message: "------- start task -------")
        
        Observable.range(start: 1, count: 10)
            .filter { $0.isMultiple(of: 2)}
            .map { "\($0)"}
            .subscribe(onNext: { [weak self] in
                self?.threadLog(message: $0)
            })
            .disposed(by: self.rx.disposeBag)
        
        timeStampedLog(message: "------- end task -------")
    }
}

extension DataEmittOperatorsViewController {
    private func timeStampedLog(message: String) {
        NSLog("Create Operator : thread name = \(Thread.isMainThread ? "Main Thread" : "Background Thread") 실행 후 흐른 시간 =  \(Date().millisecondsSince1970 - startedTime)/ message = \(message)")
    }
    
    private func threadLog<T>(message: T) {
        NSLog("scheduler : thread name = \(Thread.isMainThread ? "Main Thread" : "Background Thread" ) / message = \(message)")
    }
    private func startTaskToGetFirstString() -> String { Thread.sleep(forTimeInterval: 1); self.threadLog(message: "Start task to emit 1"); return "1"}
    private func startTaskToGetSecondString() -> String { Thread.sleep(forTimeInterval: 1); self.threadLog(message: "Start task to emit 2"); return "2"}
    private func startTaskToGetThirdString() -> String {Thread.sleep(forTimeInterval: 1); self.threadLog(message: "Start task to emit 3"); return "3"}
}


extension Date {
    var millisecondsSince1970:Int64 {
        return Int64((self.timeIntervalSince1970 * 1000.0).rounded())
    }
    
    init(milliseconds:Int64) {
        self = Date(timeIntervalSince1970: TimeInterval(milliseconds) / 1000)
    }
}
