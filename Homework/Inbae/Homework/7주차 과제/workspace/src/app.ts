import { concatMap, defer, from, fromEvent, interval, Observable, of, range, take, timer } from "rxjs";

// defer operator
console.log("* Defer observable");
console.log("---start---");

const observable = new Observable((observer) => {
  observer.next(1);
  observer.next(2);
  observer.next(3);
  observer.complete();
});

const deferObservable = defer(() => {
  return observable;
});

deferObservable.subscribe((x) => {
  console.log("defer " + x);
});

console.log("---end---\n");

// from operator
console.log("* From observable");
console.log("---start---");

const fromObservable = from([1, 2, 3, 4, 5, 6]);

fromObservable.subscribe((x) => console.log("from " + x));

console.log("---end---\n");

// interval operator
console.log("* Interval observable");
console.log("---start---");

const intervalObservable = interval(1000);
const takeFour = intervalObservable.pipe(take(4));
takeFour.subscribe((x) => console.log("interval " + x));

console.log("---end---\n");

// timer operator
console.log("* Timer observable");
console.log("---start---");

const timerObservable = timer(3000);
timerObservable.subscribe((x) => console.log("timer " + x));

console.log("---end---\n");

// range operator
console.log("* Range observable");
console.log("---start---");

const rangeObservable = range(0, 10);
rangeObservable.subscribe((x) => console.log("range  " + x));
