import { Observable, map, of } from 'rxjs';

const source = new Observable(subscriber => {
	subscriber.next(1)
	subscriber.next(2)
	subscriber.error('raise error')
	subscriber.next(3)
	subscriber.complete()
})

source.pipe(
	map(val => val)
)
	.subscribe({
		next: val => console.log('next:', val),
		error: err => console.log('err:', err),
		complete: () => console.log('----end')
	})

console.log('-------')
of(1)
	.subscribe({
		next: val => console.log('next:', val),
		error: err => console.log('err:', err),
		complete: () => console.log('----end')
	})

