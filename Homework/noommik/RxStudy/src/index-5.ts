import {
  Observable,
  subscribeOn,
  observeOn,
  from,
  tap,
  queueScheduler,
  asapScheduler,
  asyncScheduler,
  animationFrameScheduler
} from 'rxjs';

const array = ['a', 'b', 'c', 'd', 'e']
const result = from(array)

console.log('\n::Before queueScheduler\n\n')
result.pipe(
  tap(x => console.log(x, 'queue 처리 1')),
  tap(x => console.log(x, 'queue 처리 2')),
  observeOn(queueScheduler),
  tap(x => console.log(x, 'queue 처리 3')),
  tap(x => console.log(x, 'queue 처리 4')),
)
.subscribe({
  next: val => console.log('next:', val),
  error: err => console.log('err:', err),
  complete: () => console.log('----queue end')
})
console.log('\n::After queueScheduler\n\n')

console.log('\n::Before asyncScheduler\n\n')
result.pipe(
  tap(x => console.log(x, 'async 처리 1')),
  tap(x => console.log(x, 'async 처리 2')),
  observeOn(asyncScheduler, 0),
  tap(x => console.log(x, 'async 처리 3')),
  tap(x => console.log(x, 'async 처리 4')),
)
.subscribe({
  next: val => console.log('next:', val),
  error: err => console.log('err:', err),
  complete: () => console.log('----async end')
})
console.log('\n::After asyncScheduler\n\n')

console.log('\n::Before asapScheduler\n\n')
result.pipe(
  tap(x => console.log(x, 'asap 처리 1')),
  tap(x => console.log(x, 'asap 처리 2')),
  observeOn(asapScheduler, 0),
  tap(x => console.log(x, 'asap 처리 3')),
  tap(x => console.log(x, 'asap 처리 4')),
)
.subscribe({
  next: val => console.log('next:', val),
  error: err => console.log('err:', err),
  complete: () => console.log('----asap end')
})
console.log('\n::After asapScheduler\n\n')
