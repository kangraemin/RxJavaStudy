import { Observer, Subject } from './Observer'

export class School implements Subject {
	private observerList: Observer[]

	constructor() {
		this.observerList = []
	}

	subscribe(observer: Observer) {
		this.observerList.push(observer)
	}

	unsubscribe(observer: Observer) {
		this.observerList = this.observerList.filter(subscriber => subscriber !== observer);
	}

	notify() {
		for (const observer of this.observerList) {
			observer.update()
		}
	}
}
