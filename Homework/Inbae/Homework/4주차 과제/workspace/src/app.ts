import { Observable, last } from "rxjs";

function createObserver(onError = false) {
  return new Observable((subscriber) => {
    subscriber.next(1);
    subscriber.next(2);
    subscriber.next(3);

    if (onError) {
      subscriber.error("error occurred");
    }

    subscriber.complete();
  });
}

// observer with onNext handler
console.log("* observer with onNext handler");
const observerOnlyNext = createObserver();
observerOnlyNext.pipe(last()).subscribe({
  next(x) {
    console.log(`got value: ${x}`);
  },
});

// observer with onNext, onError handlers
console.log("* observer with onNext, onError handlers");
const observerWithOnError = createObserver(true);
observerWithOnError.pipe(last()).subscribe({
  next(x) {
    console.log(`got value: ${x}`);
  },
  error(err) {
    console.log(`got error: ${err}`);
  },
});

// observer with onNext, onError, onComplete handlers
console.log("* observer with onNext, onError, onComplete handlers");
const observerWithOnErrorAndOnComplete = createObserver();
observerWithOnErrorAndOnComplete.pipe(last()).subscribe({
  next(x) {
    console.log(`got value: ${x}`);
  },
  error(err) {
    console.log(`got error: ${err}`);
  },
  complete() {
    console.log(`completed`);
  },
});
