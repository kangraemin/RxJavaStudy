import { Observable, observeOn, asyncScheduler, queueScheduler, asapScheduler, animationFrameScheduler, of, map, subscribeOn } from "rxjs";

function log<T>(message: T) {
  console.log(`Message: ${message}`);
}

const observable = new Observable((observer) => {
  observer.next(1);
  observer.next(2);
  observer.next(3);
  observer.complete();
});

// asyncScheduler 적용
console.log("asyncScheduler: just before subscribe");
observable
  .pipe(
    map((x) => `asyncScheduler: after map operator - ${x}`),
    subscribeOn(asyncScheduler)
  )
  .subscribe((x) => log<typeof x>(x));
console.log("asyncScheduler: just after subscribe");

// asapScheduler 적용 -> asyncScheduler가 먼저 실행됐지만 asapScheduler가 적용된 스트림이 먼저 방출되는 것을 확인할 수 있다.
console.log("asapScheduler: just before subscribe");
observable
  .pipe(
    map((x) => `asapScheduler: after map operator - ${x}`),
    subscribeOn(asapScheduler)
  )
  .subscribe((x) => log<typeof x>(x));
console.log("asapScheduler: just after subscribe");

// queueScheduler 적용 -> queueScheduler는 interval이 주어지지 않으면 동기적으로 동작하므로 asyncScheduler와 asapScheduler 보다 나중에 스트림이 생성되더라도 가장 먼저 이벤트가 방출된 것을 확인할 수 있다.
console.log("queueScheduler: just before subscribe");
observable
  .pipe(
    map((x) => `queueScheduler: after map operator - ${x}`),
    subscribeOn(queueScheduler),
    observeOn(asapScheduler)
  )
  .subscribe((x) => log<typeof x>(x));
console.log("queueScheduler: just after subscribe");

// queueScheduler로 스케줄러를 지정했지만 observeOn 연산자를 통해 asapScheduler로 교체했다. 결과를 확인해보면 위의 queueScheduler 다음에 이 스트림 결과가 방출되는 것을 확인할 수 있다.
console.log("queueScheduler but changed to asapScheduler: just before subscribe");
observable
  .pipe(
    map((x) => `queueScheduler but changed to asapScheduler: after map operator - ${x}`),
    subscribeOn(queueScheduler),
    observeOn(asapScheduler)
  )
  .subscribe((x) => log<typeof x>(x));
console.log("queueScheduler but changed to asapScheduler: just after subscribe");

// 결과론적으로는 asyncScheduler가 가장 마지막으로 이벤트를 방출하는 것을 확인할 수 있다. RxJS에서 스케줄러는 하나의 싱글 쓰레드에 비동기적인 데이터를 어느 순서로 처리할지에 대해서 구현돼 있는 것을 확인할 수 있다.
