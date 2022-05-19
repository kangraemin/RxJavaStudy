import {
  Subject,
  AsyncSubject,
  BehaviorSubject,
  ReplaySubject,
} from 'rxjs';

// Subject
console.log('------Subject')
const subject = new Subject()

subject.next(1)
subject.subscribe(console.log)
subject.next(2) // result: 2
subject.subscribe(console.log)
subject.next(3) // result: 3, 3

// AsyncSubject
console.log('------AsyncSubject')
const asyncSubject = new AsyncSubject()

asyncSubject.subscribe(console.log)
asyncSubject.next(11)
asyncSubject.subscribe(console.log)
asyncSubject.next(22)
asyncSubject.next(33)
asyncSubject.complete() // result: 33, 33

// BehaviorSubject
console.log('------BehaviorSubject')
const behaviorSubject = new BehaviorSubject(111)

behaviorSubject.subscribe(console.log) // result: 111
behaviorSubject.next(222) // result: 222
behaviorSubject.next(333) // result: 333
behaviorSubject.subscribe(console.log) // result: 333
behaviorSubject.next(444) // result: 444

// BehaviorSubject
console.log('------ReplaySubject')
const replaySubject = new ReplaySubject(2)

replaySubject.subscribe(console.log)
replaySubject.next(2222) // result: 2222
replaySubject.next(3333) // result: 3333
replaySubject.next(4444) // result: 4444
replaySubject.subscribe(console.log) // result: 3333, 4444
replaySubject.next(5555) // result: 5555, 5555
