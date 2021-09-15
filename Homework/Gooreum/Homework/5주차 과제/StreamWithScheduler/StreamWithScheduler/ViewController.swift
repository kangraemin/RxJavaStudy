//
//  ViewController.swift
//  StreamWithScheduler
//
//  Created by Mingu Seo on 2021/09/10.
//

import UIKit
import RxSwift
import NSObject_Rx
import OSLog

enum ErrorCase: Error {
    case Observable
    case Flowable
    case Single
    case Completable
    case Maybe
}

class ViewController: UIViewController {
    
    @IBOutlet weak var btnObservable: UIButton!
    @IBOutlet weak var btnSingle: UIButton!
    @IBOutlet weak var btnCompletable: UIButton!
    @IBOutlet weak var btnMaybe: UIButton!
    
    let concurrentGlobalScheduler = ConcurrentDispatchQueueScheduler(queue:DispatchQueue.global())
    let concurrentMainScheduler = ConcurrentDispatchQueueScheduler(queue:DispatchQueue.main)
    let serialScheduler = SerialDispatchQueueScheduler(internalSerialQueueName: "serial")
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    //MARK: Observable
    @IBAction func btnObservable(_ sender: Any) {
        
        Observable<String>.just("Observable Just") 
            .subscribe(on: MainScheduler.instance)
            .observe(on: CurrentThreadScheduler.instance)
            .observe(on: SerialDispatchQueueScheduler.init(internalSerialQueueName: "A"))
            .observe(on: concurrentGlobalScheduler)
            
            .subscribe { [self] in
                threadLog(message: $0)
            }
            .disposed(by: self.rx.disposeBag)
        
        self.createObservable(value: "Observable Create")
            .subscribe(on: MainScheduler.instance)
            .observe(on: CurrentThreadScheduler.instance)
            .observe(on: SerialDispatchQueueScheduler.init(internalSerialQueueName: "B"))
            .observe(on: concurrentGlobalScheduler)
            .subscribe { [self] in
                threadLog(message: $0)
            }
            .disposed(by: self.rx.disposeBag)
        
    }
    
    //MARK: Single
    @IBAction func btnSingle(_ sender: Any) {
        self.createSingle(value: "Single Create")
            .subscribe(on: concurrentGlobalScheduler)
            .observe(on: concurrentMainScheduler)
            .subscribe {
                switch $0 {
                case .success(let value) :
                    self.threadLog(message: value)
                case .failure(let error) :
                    self.threadLog(message: error.localizedDescription)
                }
            }
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK: Completable
    @IBAction func btnCompletable(_ sender: Any) {
        self.createCompletable()
            .subscribe(on: concurrentGlobalScheduler)
            .observe(on: concurrentMainScheduler)
            .subscribe { [self] in
                switch $0 {
                case .completed :
                    threadLog(message: "Completable is Completed")
                case .error(let error) :
                    print(error.localizedDescription)
                    fatalError()
                }
            }
            .disposed(by: self.rx.disposeBag)
    }
    
    //MARK:Maybe
    @IBAction func btnMaybe(_ sender: Any) {
        self.createMaybe(value: "Maybe Create")
            .subscribe(on: concurrentMainScheduler)
            .observe(on: serialScheduler)
            .subscribe( 
                onSuccess: {[self] in threadLog(message: $0)},
                onError: {[self] in threadLog(message: $0.localizedDescription)},
                onCompleted: {[self] in threadLog(message: "Maybe is Completed")})
            .disposed(by: self.rx.disposeBag)
    }
}

extension ViewController {
    private func threadLog<T>(message: T) {
        print("scheduler : ", "thread name = \(Thread.isMainThread ? "Main Thread" : "Background Thread" ) / message = \(message)")
    }
    
    private func createObservable<T>(value: T) -> Observable<T> {
        return Observable.create { [self] in
            threadLog(message: "Emit Observable Create Event")
            $0.onNext(value)
            $0.onError(ErrorCase.Completable)
            $0.onCompleted()
            
            return Disposables.create()
        }
    }
    
    private func createSingle<T>(value: T) -> Single<T> {
        return Single.create{ [self] in
            $0(.success(value))
            $0(.failure(ErrorCase.Single))
            threadLog(message: "Emit Single Event")
            return Disposables.create()
        }
    }
    
    private func createCompletable() -> Completable {
        return Completable.create { [self] in
            $0(.completed)
            $0(.error(ErrorCase.Completable))
            threadLog(message: "Emit Completable Event")
            return Disposables.create()
        }
    }
    
    private func createMaybe<T>(value: T) -> Maybe<T> {
        return Maybe.create { [self] in
            $0(.success(value))
            $0(.error(ErrorCase.Maybe))
            $0(.completed)
            threadLog(message: "Emit Maybe Event")
            return Disposables.create()
        }
    }
}
